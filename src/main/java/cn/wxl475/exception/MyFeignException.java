package cn.wxl475.exception;

import lombok.Data;

@Data
public class MyFeignException extends RuntimeException {
    private String msg;

    public MyFeignException(String msg) {
        this.msg=msg;
    }
}
