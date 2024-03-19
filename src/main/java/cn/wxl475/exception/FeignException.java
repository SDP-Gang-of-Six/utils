package cn.wxl475.exception;

public class FeignException extends MyException {
    public FeignException(String msg) {
        super("被调用服务异常："+msg);
    }
}
