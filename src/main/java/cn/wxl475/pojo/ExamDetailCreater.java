package cn.wxl475.pojo;

import cn.wxl475.pojo.enums.optionType;
import com.baomidou.mybatisplus.annotation.TableField;

public class ExamDetailCreater {
    private Long questionId;
    //选择题
    private optionType option;
    //判断题
    private boolean judge;
    //填空题
    private String blank;
}
