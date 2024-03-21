package cn.wxl475.utils;

import cn.wxl475.pojo.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public static ArrayList<ExamDetail> convertExamCreatersToExamDetails(ExamCreater examCreater) {
        ArrayList<ExamDetail> examDetails = new ArrayList<>();
        for (ExamDetailCreater examDetailCreater : examCreater.getExamDetailCreaters()) {
            ExamDetail examDetail = new ExamDetail();
            examDetail.setExamId(examCreater.getExamId());
            examDetail.setQuestionId(examDetailCreater.getQuestionId());
            if (examDetailCreater.getOption() != null) {
                examDetail.setOption(examDetailCreater.getOption());
            }else if (examDetailCreater.getBlank() != null) {
                examDetail.setBlank(examDetailCreater.getBlank());
            }else {
                examDetail.setJudge(examDetailCreater.isJudge());
            }
            examDetails.add(examDetail);
        }
        return examDetails;
    }

    public static HashMap<Long,Integer> convertPaperCreaterToMap(PaperCreater paperCreater) {
        HashMap<Long,Integer> map = new HashMap<>();
        for (PaperScoreCreater paperScoreCreater : paperCreater.getPaperScores()) {
            map.put(paperScoreCreater.getQuestionId(),paperScoreCreater.getScore());
        }
        return map;
    }

    public static ArrayList<ExamDetail> mixExamDetails(List<ExamDetail> oldExamDetails, ArrayList<ExamDetail> newExamDetails) {
        ArrayList<ExamDetail> examDetails = newExamDetails;
        HashMap<Long,ExamDetail> map = new HashMap<>();
        for (ExamDetail examDetail : newExamDetails) {
            map.put(examDetail.getQuestionId(),examDetail);
        }
        for (ExamDetail examDetail : oldExamDetails) {
            if (!map.containsKey(examDetail.getQuestionId())) {
                examDetails.add(examDetail);
            }
        }
        return examDetails;
    }
}
