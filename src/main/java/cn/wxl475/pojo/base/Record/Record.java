package cn.wxl475.pojo.base.Record;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

// 档案管理
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "record", schema = "base", autoResultMap = true)
@Document(indexName = "record")
public class Record {
    @TableId(type = IdType.ASSIGN_ID)
    @Id
    @Field(type = FieldType.Long, store = true,index = false)
    private Long recordId;
    @Field(type = FieldType.Keyword, store = true)
    private String recordType;
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String recordName;
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String content;
    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT)
    @Field(type = FieldType.Keyword, store = true,index = false)
    private String createTime;
    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Field(type = FieldType.Keyword, store = true,index = false)
    private String updateTime;
    @TableLogic
    @Field(type = FieldType.Boolean, store = true,index = false)
    private Boolean deleted;
}
