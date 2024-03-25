package cn.wxl475.exception;

public class FileNotFoundException extends MyException{
    public FileNotFoundException(String msg) {
        super("文件找不到"+msg);
    }
}
