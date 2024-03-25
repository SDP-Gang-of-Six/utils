package cn.wxl475.exception;

public class FileCopyException extends MyException{
    public FileCopyException(String msg) {
        super("File.copy调用时，文件拷贝错误"+msg);
    }
}
