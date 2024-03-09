package cn.wxl475.mysql;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class DataSourceAop {

    @Pointcut("@annotation(cn.wxl475.mysql.ReadOnly)")
    public void readPointcut(){}

    /**
     * 配置前置通知，切换数据源为从数据库
     */
    @Before("readPointcut()")
    public void readAdvise(){
        log.info("切换数据源为从数据库");
        DynamicDbUtil.slave();
    }

}
