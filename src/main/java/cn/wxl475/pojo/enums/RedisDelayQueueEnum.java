package cn.wxl475.pojo.enums;

import lombok.Getter;

@Getter
public enum RedisDelayQueueEnum {

    EXAM_AUTO_SUBMIT("EXAM_AUTO_SUBMIT","考试自动提交", "examAutoSubmit");

    /**
     * 延迟队列 Redis Key
     */
    private String code;

    /**
     * 中文描述
     */
    private String name;

    /**
     * beanId
     */
    private String beanId;


    RedisDelayQueueEnum(String code, String name, String beanId) {
        this.code = code;
        this.name = name;
        this.beanId = beanId;
    }

}