package cn.wxl475.pojo.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamOut {
    private Exam exam;
    private Paper paper;
    private ArrayList<Question> questions;
    private String nickname;
}
