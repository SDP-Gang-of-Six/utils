package cn.wxl475.pojo;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "illness", schema = "case")
public class Illness {
    @TableId(type = IdType.ASSIGN_ID)
    private Long illnessId;

    private String illnessName;

    private Integer illnessType;

    private String content;

    private String imageUrl;

    private String videoUrl;

    private Boolean deleted;

    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
