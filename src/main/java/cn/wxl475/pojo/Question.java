package cn.wxl475.pojo;

import cn.wxl475.pojo.enums.optionType;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "question")
@TableName(value = "question",schema = "exam")
public class Question {
    @TableId(type = IdType.ASSIGN_ID)
    @Id
    @Field(type = FieldType.Long, store = true,index = false)
    private Long questionId;
    @TableField("question_type")
    @Field(type = FieldType.Keyword, store = true)
    private cn.wxl475.pojo.enums.questionType questionType;
    @TableField("description")
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String description;
    @TableField("tag")
    @Field(type = FieldType.Keyword, store = true)
    private String tag;
    
    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT)
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String createTime;
    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String updateTime;

    //选择题
    @TableField("option_A")
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String optionA;
    @TableField("option_B")
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String optionB;
    @TableField("option_C")
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String optionC;
    @TableField("option_D")
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String optionD;
    @TableField("right_option")
    @Field(type = FieldType.Keyword, store = true)
    private optionType rightOption;
    //判断题
    @TableField("right_judge")
    @Field(type = FieldType.Keyword, store = true)
    private boolean rightJudge;
    //填空题
    @TableField("right_blank")
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String rightBlank;
}
