package cn.wxl475.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ExamCreater {

    private Long examId;
    private LocalDateTime submitTime;
    ArrayList<ExamDetailCreater> examDetailCreaters;
}
