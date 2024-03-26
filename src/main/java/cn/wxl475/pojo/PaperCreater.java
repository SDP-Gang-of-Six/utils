package cn.wxl475.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaperCreater {
    private Long paperId;
    private String paperName;
    private Integer examTime;
    private Integer totalScore;
    private String createTime;
    private String updateTime;
    private String paperDescription;
    private ArrayList<PaperScoreCreater> paperScores;
}
