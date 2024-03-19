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
            paperScore.setPaperscore(paperScoreCreater.getScore());
            paperScores.add(paperScore);
        }
        return paperScores;
    }

    public static PaperCreater convertPaperToPaperCreater(Paper paper, ArrayList<PaperScore> paperScores) {
        PaperCreater paperCreater = new PaperCreater();
        paperCreater.setPaperId(paper.getPaperId());
        paperCreater.setPaperName(paper.getPaperName());
        paperCreater.setExamTime(paper.getExamTime());
        paperCreater.setTotalScore(paper.getTotalScore());
        paperCreater.setCreateTime(paper.getCreateTime());
        paperCreater.setUpdateTime(paper.getUpdateTime());
        paperCreater.setPaperScores(convertPaperScoresToPaperScoreCreaters(paperScores));
        return paperCreater;
    }

    public static ArrayList<PaperScoreCreater> convertPaperScoresToPaperScoreCreaters(ArrayList<PaperScore> paperScores) {
        ArrayList<PaperScoreCreater> paperScoreCreaters = new ArrayList<>();
        for (PaperScore paperScore : paperScores) {
            PaperScoreCreater paperScoreCreater = new PaperScoreCreater();
            paperScoreCreater.setQuestionId(paperScore.getQuestionId());
            paperScoreCreater.setScore(paperScore.getPaperscore());
            paperScoreCreaters.add(paperScoreCreater);
        }
        return paperScoreCreaters;
    }

}
