package cn.wxl475.pojo;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "illness", schema = "illness", autoResultMap = true)
public class Illness {
    @TableId(type = IdType.ASSIGN_ID)
    private Long illnessId;

    private String illnessName;

    private Integer illnessType;

    private String content;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Long> imageIds;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Long> videoIds;

    @TableLogic
    private Boolean deleted;

    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
