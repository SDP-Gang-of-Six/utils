package cn.wxl475.pojo.exam;

import cn.wxl475.pojo.enums.OptionType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "examdetail",schema = "exam")
public class ExamDetail {
    @TableField("exam_id")
    private Long examId;
    @TableField("question_id")
    private Long questionId;
    @TableField("is_right")
    private boolean isRight;
    //选择题
    @TableField("your_option")
    private OptionType yourOption;
    //判断题
    @TableField("judge")
    private Boolean judge;
    //填空题
    @TableField("blank")
    private String blank;
}
