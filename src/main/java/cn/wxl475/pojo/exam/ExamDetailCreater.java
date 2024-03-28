package cn.wxl475.pojo.exam;

import cn.wxl475.pojo.enums.OptionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDetailCreater {
    private Long questionId;
    //选择题
    private OptionType option;
    //判断题
    private boolean judge;
    //填空题
    private String blank;
}
