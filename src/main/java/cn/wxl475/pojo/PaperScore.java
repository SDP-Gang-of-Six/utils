package cn.wxl475.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "paperScore",schema = "exam")
public class PaperScore {
    @TableField("paper_id")
    private Long paperId;
    @TableField("question_id")
    private Long questionId;
    @TableField("paper_Score")
    private Integer score;
}
