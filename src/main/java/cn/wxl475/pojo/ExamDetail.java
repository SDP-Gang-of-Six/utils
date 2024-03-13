package cn.wxl475.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "examDetail",schema = "exam")
public class ExamDetail {
    @TableField("exam_id")
    private Long examId;
    @TableField("question_id")
    private Long questionId;
    @TableField("is_right")
    private boolean isRight;
    @TableField("score")
    private Integer score;
    //选择题
    @TableField("option")
    private optionType option;
    //判断题
    @TableField("judge")
    private boolean judge;
    //填空题
    @TableField("blank")
    private String blank;
}
