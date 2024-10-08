package cn.wxl475.pojo.base;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "medicines",schema = "base")
@Document(indexName = "medicines")
public class Medicine {
    @TableId(type = IdType.ASSIGN_ID)
    @Id
    @Field(type = FieldType.Long, store = true,index = false)
    private Long medicineId;
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String medicineName;
    @Field(type = FieldType.Keyword, store = true)
    private String medicineUsage;
    @Field(type = FieldType.Keyword, store = true)
    private String medicinePrescriptionCategory;
    @Field(type = FieldType.Keyword, store = true)
    private Integer medicineStock;
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String medicineDetail;
    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT)
    @Field(type = FieldType.Keyword, store = true,index = false)
    private String createTime;
    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Field(type = FieldType.Keyword, store = true,index = false)
    private String updateTime;
}
