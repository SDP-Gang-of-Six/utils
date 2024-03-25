package cn.wxl475.exception;

public class FileTransferToException extends MyException{
    public FileTransferToException(String msg) {
        super("File.transferTo调用时，文件IO错误"+msg);
    }
}
