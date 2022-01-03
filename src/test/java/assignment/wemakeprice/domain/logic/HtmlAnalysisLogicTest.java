package assignment.wemakeprice.domain.logic;

import assignment.wemakeprice.domain.query.HtmlAnalysisQuery;
import assignment.wemakeprice.domain.vo.AnalysisType;
import assignment.wemakeprice.domain.vo.HtmlAnalysis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class HtmlAnalysisLogicTest {

    @Autowired
    private HtmlAnalysisLogic htmlAnalysisLogic;

    @Test
    public void analyze() {
        URL htmlUrl = HtmlAnalysisLogicTest.class.getResource("/html/index.html");
        assert htmlUrl != null;
        String url = htmlUrl.toString();

        HtmlAnalysis htmlAnalysisWithoutHtmlTag = htmlAnalysisLogic.analyze(new HtmlAnalysisQuery(
                url,
                50,
                AnalysisType.WithoutHtmlTag
        ));

        String quotientNoTag = htmlAnalysisWithoutHtmlTag.getQuotientString();
        String remainderNoTag = htmlAnalysisWithoutHtmlTag.getRemainderString();
        int remainderStringLength = remainderNoTag.getBytes().length;
        int quotientStringLength = quotientNoTag.getBytes().length;

        assertEquals(0, quotientStringLength);
        assertEquals(10, remainderStringLength);
        assertEquals("a0b1ddehoy", remainderNoTag);

        HtmlAnalysis htmlAnalysisAllText = htmlAnalysisLogic.analyze(new HtmlAnalysisQuery(
                url,
                10,
                AnalysisType.AllText
        ));

        String quotientAllText = htmlAnalysisAllText.getQuotientString();

        assertEquals("a0a1a8aabbbCcDddddddEeeeeefhhhhhhhlllmmmmOoooPrSTttttttuYyyy", quotientAllText);
    }
}
