package cn.wxl475.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "exam",schema = "exam")
public class Exam {
    @TableId(type = IdType.ASSIGN_ID)
    private Long examId;
    @TableField("paper_id")
    private Long paperId;
    @TableField("user_id")
    private Long userId;
    @TableField("submit_time")
    private LocalDateTime submitTime;
    @TableField("exam_score")
    private Integer examScore;


    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
