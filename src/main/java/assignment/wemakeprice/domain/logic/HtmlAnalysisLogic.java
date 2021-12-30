package assignment.wemakeprice.domain.logic;

import assignment.wemakeprice.domain.logic.helper.AsciiCodeUtils;
import assignment.wemakeprice.domain.logic.helper.SortHelper;
import assignment.wemakeprice.domain.query.HtmlAnalysisQuery;
import assignment.wemakeprice.domain.vo.AnalysisType;
import assignment.wemakeprice.domain.vo.HtmlAnalysis;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HtmlAnalysisLogic {

    public HtmlAnalysis analyze(HtmlAnalysisQuery query) {

        List<Integer> numbers = new ArrayList<>();
        List<Integer> alphabets = new ArrayList<>();
        AnalysisType type = query.getType();

        try {
            URL url = new URL(query.getUrl());

            InputStream inputStream = url.openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            if (AnalysisType.AllText.equals(type)) {
                filterAllAlphaNumerics(bufferedReader, numbers, alphabets);
            } else if (AnalysisType.WithoutHtmlTag.equals(type)) {
                filterWithoutHtmlTag(bufferedReader, numbers, alphabets);
            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        SortHelper.sortAscending(numbers);
        SortHelper.sortAscending(alphabets, AsciiCodeUtils::mapToAlphabeticalOrder);

        StringBuilder builder = new StringBuilder();
        Iterator<Integer> numberIterator = numbers.iterator();
        Iterator<Integer> alphabetIterator = alphabets.iterator();

        while (numberIterator.hasNext() || alphabetIterator.hasNext()) {
            if (alphabetIterator.hasNext()) {
                builder.append(Character.toString(alphabetIterator.next()));
            }
            if (numberIterator.hasNext()) {
                builder.append(Character.toString(numberIterator.next()));
            }
        }

        int numberLength = numbers.size();
        int alphabetLength = alphabets.size();
        int totalLength = numberLength + alphabetLength;
        int divisor = query.getDivisor();
        int quotient = totalLength - (totalLength % divisor) ;

        String quotientString = builder.substring(0, quotient);
        String remainderString = builder.substring(quotient);

        return new HtmlAnalysis(
                quotientString,
                remainderString
        );
    }

    private void filterAllAlphaNumerics(BufferedReader reader, List<Integer> numbers, List<Integer> alphabets) throws IOException {
        String line;

        while ((line = reader.readLine()) != null) {
            List<Integer> asciiCodes = line.chars().boxed().collect(Collectors.toList());

            for (int asciiCode : asciiCodes) {
                if (AsciiCodeUtils.isNumber(asciiCode)) {
                    numbers.add(asciiCode);
                } else if (AsciiCodeUtils.isAlphabet(asciiCode)) {
                    alphabets.add(asciiCode);
                }
            }
        }

        reader.close();
    }

    private void filterWithoutHtmlTag(BufferedReader reader, List<Integer> numbers, List<Integer> alphabets) throws IOException {
        String line;
        boolean tagFlag = false;

        while ((line = reader.readLine()) != null) {
            List<Integer> asciiCodes = line.chars().boxed().collect(Collectors.toList());

            for (int asciiCode : asciiCodes) {

                if (AsciiCodeUtils.isLeftAngleBracket(asciiCode)) {
                    tagFlag = true;
                    continue;
                } else if (AsciiCodeUtils.isRightAngleBracket(asciiCode)){
                    tagFlag = false;
                    continue;
                }

                if (tagFlag) {
                    continue;
                }

                if (AsciiCodeUtils.isNumber(asciiCode)) {
                    numbers.add(asciiCode);
                } else if (AsciiCodeUtils.isAlphabet(asciiCode)) {
                    alphabets.add(asciiCode);
                }
            }
        }

        reader.close();
    }
}
