package cn.wxl475.exception;

public class FileIOException extends MyException{
    public FileIOException(String msg) {
        super("文件操作异常"+msg);
    }
}
