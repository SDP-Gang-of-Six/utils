package cn.wxl475.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试延时队列
 *
 */
@Slf4j
@Component
public class ExamDelayQueue extends AbstractDelayQueueMachineFactory {

    /**
     * 处理业务逻辑
     */
    @Override
    public void invoke(String jobId) {

    }

    /**
     * 延时队列名统一设定
     */
    @Override
    public String setDelayQueueName() {
        return "exam_delay_queue";
    }
}
