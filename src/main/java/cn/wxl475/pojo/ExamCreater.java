package cn.wxl475.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamCreater {
    private Long examId;
    private LocalDateTime submitTime;
    private Long paperId;
    private LocalDateTime startTime;
    private Integer duration;
    private ArrayList<ExamDetailCreater> examDetailCreaters;
}
