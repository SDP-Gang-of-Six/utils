package cn.wxl475.pojo.base.Charge;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

// 收费管理
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "charge", schema = "base", autoResultMap = true)
@Document(indexName = "charge")
public class Charge {
    @TableId(type = IdType.ASSIGN_ID)
    @Id
    @Field(type = FieldType.Long, store = true,index = false)
    private Long chargeId;
    @Field(type = FieldType.Keyword, store = true)
    private String payer;
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String illnessName;
    @Field(type = FieldType.Keyword, store = true)
    private String illnessType;
    @Field(type = FieldType.Integer, store = true, index = false)
    private Integer money;
    @Field(type = FieldType.Keyword, store = true)
    private String petType;
    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT)
    @Field(type = FieldType.Keyword, store = true,index = false)
    private String createTime;
    @TableLogic
    @Field(type = FieldType.Boolean, store = true,index = false)
    private Boolean deleted;
}
