package cn.wxl475.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import cn.wxl475.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class AbstractDelayQueueMachineFactory {


    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ThreadPoolTaskExecutor asyncTaskExecutor;

    /**
     * 插入任务id
     *
     * @param jobId 任务id(队列内唯一)
     * @param time  延时时间(单位 :毫秒)
     * @return 是否插入成功
     */
    public boolean addJob(String jobId, Integer time) {
        Calendar instance = Calendar.getInstance();
        //增加延时时间,获取最终触发时间
        instance.add(Calendar.MILLISECOND, time);
        long delayMillisecond = instance.getTimeInMillis();
        log.info("延时队列添加问题{}",jobId);
        return redisUtil.zAdd(setDelayQueueName(), delayMillisecond, jobId);

    }

    /**
     * 删除任务id
     *
     * @param jobId 任务id(队列内唯一)
     */
    public boolean removeJob(String jobId) {
        Long num = redisUtil.zRemove(setDelayQueueName(), jobId);
        if (num > 0) return true;
        return false;

    }

    /**
     * 延时队列机器开始运作
     */
    private void startDelayQueueMachine() {
        log.info("延时队列{}开始启动", setDelayQueueName());

        // 监听redis队列
        while (true) {
            try {
                // 获取当前时间前的任务列表
                Set<ZSetOperations.TypedTuple<Object>> tuples = redisUtil.zRangeByScore(setDelayQueueName(), 0, System.currentTimeMillis() );

                // 如果任务不为空
                if (!CollectionUtils.isEmpty(tuples)) {
                    log.info("延时任务开始执行:{}", JSONUtil.toJsonStr(tuples));
                    Iterator<ZSetOperations.TypedTuple<Object>> iterator = tuples.iterator();
                    while (iterator.hasNext()){
                        ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
                        String jobId = Convert.toStr(typedTuple.getValue());
                        // 移除缓存，如果移除成功则表示当前线程处理了延时任务，则执行延时任务
                        // 删除成功才执行延时任务，否则不执行，这样可以避免分布式系统延时任务多次执行
                        Long num = redisUtil.zRemove(setDelayQueueName(), jobId);
                        // 如果移除成功, 则执行
                        if (num > 0) {
                            asyncTaskExecutor.execute(() -> invoke(jobId));
                        }
                    }
                }

            } catch (Exception e) {
                log.error("处理延时任务发生异常,异常原因为{}", e.getMessage(), e);
            } finally {
                // 间隔()秒执行一次
                //根据业务场景设置对应时间
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    /**
     * 最终执行的任务方法
     *
     * @param jobId 任务id
     */
    public abstract void invoke(String jobId);


    /**
     * 要实现延时队列的名字
     */
    public abstract String setDelayQueueName();


    //Spring Boot初始化时开启一条线程运行
    @PostConstruct
    public void init() {
        new Thread(this::startDelayQueueMachine).start();
    }

}