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

    // 症状
    private String symptom;

    // 检查过程
    private String process;

    // 诊断结果
    private String consequence;

    // 治疗方案
    private String schedule;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Long> imageIds;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Long> videoIds;

    @TableLogic
    private Boolean deleted;

    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT)
    private String createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateTime;
}
