package assignment.wemakeprice.domain.query;

import assignment.wemakeprice.domain.vo.AnalysisType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HtmlAnalysisQuery {
    private String url;
    private int divisor;
    private AnalysisType type;
}
