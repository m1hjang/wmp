package assignment.wemakeprice.api.query;

import assignment.wemakeprice.domain.logic.HtmlAnalysisLogic;
import assignment.wemakeprice.domain.query.HtmlAnalysisQuery;
import assignment.wemakeprice.domain.vo.AnalysisType;
import assignment.wemakeprice.domain.vo.HtmlAnalysis;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HtmlAnalysisController {

    private final HtmlAnalysisLogic htmlAnalysisLogic;

    public HtmlAnalysisController(HtmlAnalysisLogic htmlAnalysisLogic) {
        this.htmlAnalysisLogic = htmlAnalysisLogic;
    }

    @GetMapping("/html-analysis")
    public HtmlAnalysis analyze(@RequestParam String url,
                                @RequestParam int divisor,
                                @RequestParam AnalysisType type) {

        return htmlAnalysisLogic.analyze(new HtmlAnalysisQuery(
                url,
                divisor,
                type
        ));
    }
}
