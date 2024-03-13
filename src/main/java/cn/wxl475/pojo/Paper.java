package cn.wxl475.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "paper",schema = "exam")
public class Paper {
    @TableId(type = IdType.ASSIGN_ID)
    private Long paperId;
    @TableField("paper_name")
    private String paperName;
    @TableField("exam_time")
    private Integer examTime;
    @TableField("total_score")
    private Integer totalScore;

    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
