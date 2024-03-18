package cn.wxl475.utils;

import cn.wxl475.pojo.Paper;
import cn.wxl475.pojo.PaperCreater;
import cn.wxl475.pojo.PaperScore;
import cn.wxl475.pojo.PaperScoreCreater;

import java.util.ArrayList;

public class ConvertUtil {
    public static Paper convertPaperCreaterToPaper(PaperCreater paperCreater) {
        Paper paper = new Paper();
        paper.setPaperId(paperCreater.getPaperId());
        paper.setPaperName(paperCreater.getPaperName());
        paper.setExamTime(paperCreater.getExamTime());
        paper.setTotalScore(paperCreater.getTotalScore());
        paper.setCreateTime(paperCreater.getCreateTime());
        paper.setUpdateTime(paperCreater.getUpdateTime());
        return paper;
    }
    public static ArrayList<PaperScore> convertPaperScoreCreatersToPaperScores(ArrayList<PaperScoreCreater> paperScoreCreaters, Long paperId) {
        ArrayList<PaperScore> paperScores = new ArrayList<>();
        for (PaperScoreCreater paperScoreCreater : paperScoreCreaters) {
            PaperScore paperScore = new PaperScore();
            paperScore.setPaperId(paperId);
            paperScore.setQuestionId(paperScoreCreater.getQuestionId());
            paperScore.setScore(paperScoreCreater.getScore());
            paperScores.add(paperScore);
        }
        return paperScores;
    }
}
