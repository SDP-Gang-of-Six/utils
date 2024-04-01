package cn.wxl475.exception;

public class ExamSolveException extends MyException {
    public ExamSolveException(String msg) {
        super("试卷处理异常："+msg);
    }
}
