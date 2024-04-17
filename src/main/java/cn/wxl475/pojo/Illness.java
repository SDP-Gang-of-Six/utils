package cn.wxl475.pojo;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "illness", schema = "illness", autoResultMap = true)
@Document(indexName = "illness")
public class Illness {
    @TableId(type = IdType.ASSIGN_ID)
    @Id
    @Field(type = FieldType.Long, store = true,index = false)
    private Long illnessId;

    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String illnessName;

    @Field(type = FieldType.Keyword, store = true)
    private String illnessType;

    // 症状
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String symptom;

    // 检查过程
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String process;

    // 诊断结果
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String consequence;

    // 治疗方案
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String schedule;

    @TableField(typeHandler = JacksonTypeHandler.class)
    @Field(type = FieldType.Long, store = true,index = false)
    private List<Long> imageIds;

    @TableField(typeHandler = JacksonTypeHandler.class)
    @Field(type = FieldType.Long, store = true,index = false)
    private List<Long> videoIds;

    @TableLogic
    @Field(type = FieldType.Boolean, store = true,index = false)
    private Boolean deleted;

    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT)
    @Field(type = FieldType.Keyword, store = true)
    private String createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Field(type = FieldType.Keyword, store = true)
    private String updateTime;
}
