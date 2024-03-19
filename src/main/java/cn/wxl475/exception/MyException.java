package cn.wxl475.exception;

import lombok.Getter;

@Getter
public class MyException extends RuntimeException {
    private final String msg;

    public MyException(String msg) {
        this.msg=msg;
    }
}
