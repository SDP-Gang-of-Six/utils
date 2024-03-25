package cn.wxl475.pojo.enums;

public enum QuestionType {
    option(0),
    blank(1),
    judge(2);
    public static QuestionType getQuestionType(int i) {
        switch (i) {
            case 0:
                return option;
            case 1:
                return blank;
            case 2:
                return judge;
            default:
                return null;
        }
    }
    QuestionType(int i) {
    }
}
