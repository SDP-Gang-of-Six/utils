package cn.wxl475.pojo.exam;

import cn.wxl475.pojo.enums.OptionType;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOut {
    Question question;
    Integer score;
    private String yourOption;
    private Boolean judge;
    private String blank;
}
