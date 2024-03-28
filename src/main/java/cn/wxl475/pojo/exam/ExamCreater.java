package cn.wxl475.pojo.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamCreater {
    private Long examId;
    private ArrayList<ExamDetailCreater> examDetailCreaters;
}
