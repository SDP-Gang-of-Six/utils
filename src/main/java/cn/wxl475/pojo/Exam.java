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
    // true 为已提交 false 为未提交
    @TableField("status")
    private boolean status;
    @TableField("duration")
    private Integer duration;
    @TableField("allow_start_time")
    private LocalDateTime allowStartTime;
    @TableField("allow_end_time")
    private LocalDateTime allowEndTime;
    @TableField("start_time")
    private LocalDateTime startTime;

    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
