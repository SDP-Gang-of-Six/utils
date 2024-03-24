package cn.wxl475.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "paper",schema = "exam")
public class Paper {
    @TableId(type = IdType.ASSIGN_ID)
    @Id
    @Field(type = FieldType.Long, store = true,index = false)
    private Long paperId;
    @TableField("paper_name")
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String paperName;
    @TableField("exam_time")
    @Field(type = FieldType.Integer, store = true,index = false)
    private Integer examTime;
    @TableField("total_score")
    @Field(type = FieldType.Integer, store = true,index = false)
    private Integer totalScore;

    //注解填充字段 @TableField(.. fill = FieldFill.INSERT) 生成器策略部分也可以配置！
    @TableField(fill = FieldFill.INSERT)
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Field(type = FieldType.Text, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String updateTime;
}
