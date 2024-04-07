package cn.wxl475.pojo;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user", schema = "user")
@Document(indexName = "user")
public class User {
    //对应数据库中的主键（UUID、自增id、雪花算法、redis、zookeeper）
    @TableId(type = IdType.ASSIGN_ID)
    @Id
    @Field(type = FieldType.Long, store = true,index = false)
    private Long uid;

    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String nickname;

    @Field(type = FieldType.Text, store = true, index = false)
    private String password;

    @Field(type = FieldType.Text, store = true, index = false)
    private String username;

    @Field(type = FieldType.Boolean, store = true, index = false)
    private Boolean userType;

    @TableLogic
    @Field(type = FieldType.Boolean, store = true,index = false)
    private Boolean deleted;

    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT)
    @Field(type = FieldType.Text, store = true, index = false)
    private String createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Field(type = FieldType.Text, store = true, index = false)
    private String updateTime;

}
