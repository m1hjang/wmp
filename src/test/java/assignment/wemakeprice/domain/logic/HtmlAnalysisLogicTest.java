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

        HtmlAnalysis htmlAnalysis = htmlAnalysisLogic.analyze(new HtmlAnalysisQuery(
                url,
                10,
                AnalysisType.WithoutHtmlTag
        ));

        String quotientString = htmlAnalysis.getQuotientString();
        String remainderString = htmlAnalysis.getRemainderString();
        int remainderStringLength = remainderString.getBytes().length;
        int quotientStringLength = quotientString.getBytes().length;
        assertEquals(quotientStringLength, 0);
        assertEquals(remainderStringLength, 8);
    }
}
