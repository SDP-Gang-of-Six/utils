package cn.wxl475.exception;

import cn.wxl475.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MyException.class)//捕获自定义异常
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//返回http500状态，代表有异常发生
    public Result ex1(MyException ex){
        ex.printStackTrace();
        log.error(ex.getMsg());
        return Result.error(ex.getMsg()+"\n发生自定义异常："+ex.getClass());
    }
    @ExceptionHandler(Exception.class)//捕获所有异常
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//返回http500状态，代表有异常发生
    public Result ex(Exception ex){
        ex.printStackTrace();
        log.error(ex.getMessage());
        return Result.error("未知服务异常："+ex.getClass());
    }

}
