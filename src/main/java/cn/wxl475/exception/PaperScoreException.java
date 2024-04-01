package cn.wxl475.exception;


public class PaperScoreException extends MyException {
    public PaperScoreException(String msg) {
        super("试卷分数异常"+msg);
    }
}
