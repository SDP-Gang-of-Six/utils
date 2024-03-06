package cn.wxl475.exception;

import cn.wxl475.pojo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MyFeignException.class)//捕获来自feign的异常
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//返回http500状态，代表有异常发生
    public Result ex1(MyFeignException ex){
        ex.printStackTrace();
        return Result.error(ex.getMsg()+"……来自被调用服务的异常");
    }
    @ExceptionHandler(Exception.class)//捕获所有异常
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//返回http500状态，代表有异常发生
    public Result ex(Exception ex){
        ex.printStackTrace();
        return Result.error("对不起，操作失败");
    }

}
