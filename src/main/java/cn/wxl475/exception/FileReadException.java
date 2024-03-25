package cn.wxl475.exception;

public class FileReadException extends MyException{
    public FileReadException(String msg) {
        super(".read调用时，文件IO错误"+msg);
    }
}
