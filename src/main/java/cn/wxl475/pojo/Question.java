package cn.wxl475.pojo;

import cn.wxl475.pojo.enums.optionType;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "question",schema = "exam")
public class Question {
    @TableId(type = IdType.ASSIGN_ID)
    private Long questionId;
    @TableField("question_type")
    private cn.wxl475.pojo.enums.questionType questionType;
    @TableField("description")
    private String description;
    @TableField("tag")
    private String tag;
    
    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    //选择题
    @TableField("option_A")
    private String optionA;
    @TableField("option_B")
    private String optionB;
    @TableField("option_C")
    private String optionC;
    @TableField("option_D")
    private String optionD;
    @TableField("right_option")
    private optionType rightOption;
    //判断题
    @TableField("right_judge")
    private boolean rightJudge;
    //填空题
    @TableField("right_blank")
    private String rightBlank;
}
