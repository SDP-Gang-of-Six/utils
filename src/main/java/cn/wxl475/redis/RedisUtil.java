package cn.wxl475.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class RedisUtil {


    public final StringRedisTemplate stringRedisTemplate;

    public RedisUtil(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate=stringRedisTemplate;
    }

    /**
     * 向Zset里添加成员
     *
     * @param key   key值
     * @param score 分数，通常用于排序
     * @param value 值
     * @return 增加状态
     */
    public boolean zAdd(String key, long score, String value) {
        Boolean result = stringRedisTemplate.opsForZSet().add(key, value, score);
        return result;

    }

    /**
     * 获取 某key 下 某一分值区间的队列
     *
     * @param key  缓存key
     * @param from 开始时间
     * @param to   结束时间
     * @return 数据
     */
    public Set<ZSetOperations.TypedTuple<String>> zRangeByScore(String key, int from, long to) {
        Set<ZSetOperations.TypedTuple<String>> set = stringRedisTemplate.opsForZSet().rangeByScoreWithScores(key, from, to);
        return set;
    }

    /**
     * 移除 Zset队列值
     *
     * @param key   key值
     * @param value 删除的集合
     * @return 删除数量
     */
    public Long zRemove(String key, String... value) {
        return stringRedisTemplate.opsForZSet().remove(key, value);
    }

}
