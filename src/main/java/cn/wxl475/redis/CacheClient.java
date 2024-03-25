package cn.wxl475.redis;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.wxl475.pojo.RedisData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static cn.wxl475.redis.RedisConstants.CACHE_NULL_TTL;
import static cn.wxl475.redis.RedisConstants.LOCK_TTL;

/*
    * 缓存客户端
    * 公开方法：
    * 1.查询并设置缓存方法，回写“”值解决缓存穿透，有效期加盐解决缓存雪崩，互斥锁访问数据库解决缓存击穿
    * 2.查询List并设置缓存方法，回写“”值解决缓存穿透，有效期加盐解决缓存雪崩，互斥锁访问数据库解决缓存击穿
    * 3.更新缓存
    * 4.删除缓存方法
    * 5.有效期加盐缓存设置方法
    * 6.设置逻辑过期时间解决缓存击穿（弃用
*/

@Slf4j
@Component
public class CacheClient {
    public final StringRedisTemplate stringRedisTemplate;
    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);

    /**
     * 构造方法
     * @param stringRedisTemplate
     */
    public CacheClient(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate=stringRedisTemplate;
    }

    /**
     * 查询并设置缓存方法，回写“”值解决缓存穿透，有效期加盐解决缓存雪崩，互斥锁访问数据库解决缓存击穿
     * @param keyPrefix
     * @param lockKeyPrefix
     * @param id
     * @param type
     * @param dbFallback
     * @param time
     * @param unit
     * @return R
     */
    public <R,ID> R queryWithPassThrough(String keyPrefix, String lockKeyPrefix, ID id , Class<R> type, Function<ID,R> dbFallback,Long time, TimeUnit unit){
        String key=keyPrefix+id;
        //查询redis
        String json=stringRedisTemplate.opsForValue().get(key);
        if(StrUtil.isNotBlank(json)){
            //不是“”值
            return JSONUtil.toBean(json,type);
        }
        if(json!=null){
            //是redis中缓存的临时“”值
            return null;
        }
        //没查到缓存，也不是临时“”值，需要查询数据库
        //互斥锁上锁
        String lockKey=lockKeyPrefix+id;
        boolean isLock=tryLock(lockKey);
        R r = null;
        if(isLock){
            //查询数据库
            r = dbFallback.apply(id);
            //数据库中没有对应数据，回写空值
            if(r ==null){
                stringRedisTemplate.opsForValue().set(key,"",CACHE_NULL_TTL,TimeUnit.MINUTES);
            }
            //数据库中有数据，随机有效期设置缓存
            else {
                this.setWithRandomExpire(key,r,time,unit);
            }
            unLock(lockKey);
        }
        return r;
    }
    /**
     * 查询List并设置缓存方法，回写“”值解决缓存穿透，有效期加盐解决缓存雪崩，互斥锁访问数据库解决缓存击穿
     * @param keyPrefix
     * @param lockKeyPrefix
     * @param id
     * @param type
     * @param dbFallback
     * @param time
     * @param unit
     */
    public <R,ID> List<R> queryListWithPassThrough(String keyPrefix, String lockKeyPrefix, ID id , Class<R> type, Function<ID,List<R>> dbFallback, Long time, TimeUnit unit){
        String key=keyPrefix+id;
        //查询redis
        String json=stringRedisTemplate.opsForValue().get(key);
        if(StrUtil.isNotBlank(json)){
            //不是“”值
            return JSONUtil.toList(JSONUtil.parseArray(json),type);
        }
        if(json!=null){
            //是redis中缓存的临时“”值
            return null;
        }
        //没查到缓存，也不是临时“”值，需要查询数据库
        //互斥锁上锁
        String lockKey=lockKeyPrefix+id;
        boolean isLock=tryLock(lockKey);
        List<R> r = null;
        if(isLock){
            //查询数据库
            r = dbFallback.apply(id);
            //数据库中没有对应数据，回写空值
            if(r ==null){
                stringRedisTemplate.opsForValue().set(key,"",CACHE_NULL_TTL,TimeUnit.MINUTES);
            }
            //数据库中有数据，随机有效期设置缓存
            else {
                this.setWithRandomExpire(key,r,time,unit);
            }
            unLock(lockKey);
        }
        return r;
    }



    /**
     * 更新缓存
     * @param keyPrefix
     * @param lockKeyPrefix
     * @param id
     * @param type
     * @param dbFallback
     * @param time
     * @param unit
     */
    public <R,ID> R resetKey(String keyPrefix, String lockKeyPrefix, ID id, Class<R> type, Function<ID,R> dbFallback,Long time, TimeUnit unit){
        String lockKey = lockKeyPrefix+id;
        String Key = keyPrefix+id;
        boolean isLock=tryLock(lockKey);
        if(isLock){
            R r = dbFallback.apply(id);
            this.setWithRandomExpire(Key,r,time,unit);
            unLock(lockKey);
            return r;
        }
        return null;
    }

    /**
     * 删除缓存方法
     * @param key
     */
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 有效期加盐缓存设置方法
     * @param key
     * @param value
     * @param time
     * @param unit
     */
    public void setWithRandomExpire(String key, Object value, Long time, TimeUnit unit){
        long randomTime = time + (long)(Math.random() * 10);
        this.set(key, value, randomTime, unit);
    }

    /**
     * 设置hashMap
     * @param key
     * @param hashMap
     */
    public void setHashMap(String key, Map<Object,Object> hashMap){
        stringRedisTemplate.opsForHash().putAll(key, hashMap);
    }

    /**
     * 删除hashKey
     * @param key
     */
    public void deleteHashMap(String key){
        stringRedisTemplate.opsForHash().delete(key);
    }

    /**
     * 获取hashValue
     * @param key
     * @param mapKey
     * @return Object
     */
    public Object getHashValue(String key, Object mapKey){
        return stringRedisTemplate.opsForHash().get(key,mapKey);
    }

    /**
     * 获取hashMap
     * @param key
     * @return Map<Object, Object>
     */
    public Map<Object, Object> getHashMap(String key){
        return stringRedisTemplate.opsForHash().entries(key);
    }

    /**
     * 设置逻辑过期时间解决缓存击穿（弃用
     * @param keyPrefix
     * @param lockKeyPrefix
     * @param id
     * @param type
     * @param dbFallback
     * @param time
     * @param unit
     * @return R
     */
    @Deprecated
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

    /**
     * 普通缓存设置方法
     * @param key
     * @param value
     * @param time
     * @param unit
     */
    private void set(String key, Object value, Long time, TimeUnit unit){
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value),time,unit);
    }

    /**
     * 设置逻辑有效期缓存方法
     * @param key
     * @param value
     * @param time
     * @param unit
     * @return
     */
    private void setWithLogicalExpire(String key, Object value, Long time, TimeUnit unit){
        RedisData redisData = new RedisData();
        redisData.setData(value);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData));
    }

    /**
     * 互斥锁上锁方法
     * @param key
     * @return
     */
    private boolean tryLock(String key){
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key,"1", LOCK_TTL,TimeUnit.SECONDS);
        return BooleanUtil.isTrue(flag);
    }

    /**
     * 互斥锁解锁方法
     * @param key
     */
    private void unLock(String key){
        stringRedisTemplate.delete(key);
    }
}
