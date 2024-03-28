package cn.wxl475.pojo.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaperScoreCreater {
    private Long questionId;
    private Integer score;
}
