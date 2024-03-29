package cn.wxl475.pojo.exam;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("allow_start_time")
    private LocalDateTime allowStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("allow_end_time")
    private LocalDateTime allowEndTime;
    @TableField("start_time")
    private LocalDateTime startTime;

    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT)
    private String createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateTime;
}
