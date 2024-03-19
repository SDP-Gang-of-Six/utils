package cn.wxl475.exception;

import lombok.Data;

@Data
public class PaperScoreException extends RuntimeException {
    private String msg;

    public PaperScoreException(String msg) {
        this.msg=msg;
    }
}
