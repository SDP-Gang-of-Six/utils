package cn.wxl475.redis;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.wxl475.pojo.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import cn.wxl475.pojo.RedisData;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static cn.wxl475.redis.RedisConstants.*;

@Slf4j
@Component
public class CacheClient {
    public final StringRedisTemplate stringRedisTemplate;
    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);

    public CacheClient(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate=stringRedisTemplate;
    }

    public void set(String key, Object value, Long time, TimeUnit unit){
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value),time,unit);
    }

    public void setWithRandomExpire(String key, Object value, Long time, TimeUnit unit){
        long randomTime = time + (long)(Math.random() * 10);
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), randomTime, unit);
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    public void setWithLogicalExpire(String key, Object value, Long time, TimeUnit unit){
        RedisData redisData = new RedisData();
        redisData.setData(value);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData));
    }
    //回写“”值解决缓存穿透
    public <R,ID> R queryWithPassThrough(String keyPrefix, String lockKeyPrefix, ID id , Class<R> type, Function<ID,R> dbFallback,Long time, TimeUnit unit){
        String key=keyPrefix+id;
        //查询redis
        String json=stringRedisTemplate.opsForValue().get(key);
        if(StrUtil.isNotBlank(json)){
            return JSONUtil.toBean(json,type);
        }
        //redis中缓存的临时“”值解决缓存穿透问题
        if(json!=null){
            return null;
        }
        //查询数据库
        String lockKey=lockKeyPrefix+id;
        boolean isLock=tryLock(lockKey);
        if(isLock){
            R r = dbFallback.apply(id);
            if(r ==null){
                stringRedisTemplate.opsForValue().set(key,"",CACHE_NULL_TTL,TimeUnit.MINUTES);
            }else {
                this.setWithRandomExpire(key,r,time,unit);
            }
            unLock(lockKey);
        }
        return null;
    }

    //逻辑有效期解决缓存击穿
    public <R,ID> R queryWithLogicalExpire(String keyPrefix, String lockKeyPrefix, ID id, Class<R> type, Function<ID,R> dbFallback,Long time, TimeUnit unit){
        String key=keyPrefix+id;
        String json = stringRedisTemplate.opsForValue().get(key);
        if(StrUtil.isBlank(json)){
            return null;
        }
        RedisData redisData=JSONUtil.toBean(json,RedisData.class);
        R r =JSONUtil.toBean((JSONObject) redisData.getData(),type);
        LocalDateTime expireTime= redisData.getExpireTime();
        //判断是否过期
        if(expireTime.isAfter(LocalDateTime.now())){
            //未过期
            return r;
        }
        //已过期，需要重建缓存
        String lockKey=lockKeyPrefix+id;
        boolean isLock=tryLock(lockKey);
        if(isLock){
            CACHE_REBUILD_EXECUTOR.submit(()->{
                try{
                    R r1= dbFallback.apply(id);
                    this.setWithLogicalExpire(key,r1,time,unit);
                }
                catch (Exception e){
                    throw new RuntimeException(e);
                }
                finally {
                    unLock(lockKey);
                }
            });
        }
        return r;
    }

    public <R,ID> void resetKey(String keyPrefix, String lockKeyPrefix, ID id, Class<R> type, Function<ID,R> dbFallback,Long time, TimeUnit unit){
        String lockKey = lockKeyPrefix+id;
        String Key = keyPrefix+id;
        boolean isLock=tryLock(lockKey);
        if(isLock){
            CACHE_REBUILD_EXECUTOR.submit(()->{
                try{
                    R r1= dbFallback.apply(id);
                    this.setWithRandomExpire(Key,r1,time,unit);
                }
                catch (Exception e){
                    throw new RuntimeException(e);
                }
                finally {
                    unLock(lockKey);
                }
            });
        }
    }


    private boolean tryLock(String key){
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key,"1",LOCK_GOODS_TTL,TimeUnit.SECONDS);
        return BooleanUtil.isTrue(flag);
    }

    private void unLock(String key){
        stringRedisTemplate.delete(key);
    }
}
