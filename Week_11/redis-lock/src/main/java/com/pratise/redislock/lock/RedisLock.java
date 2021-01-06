package com.pratise.redislock.lock;

import com.alibaba.druid.util.StringUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

import java.util.Collections;

/**
 * @author : dabing
 * @date : 2021/1/6
 */
@Service
public class RedisLock {

    // lua脚本，用来释放分布式锁
    private static String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
    private static String lockValue = "1";

    private static JedisPool jedisPool = new JedisPool();

    public static String getVal(String key) {
        return jedisPool.getResource().get(key);
    }

    public static Long decrBy(String key, long count) {
        return jedisPool.getResource().decrBy(key, count);
    }


    /**
     * 加锁
     * @param key
     * @param timeout 锁超时时间，单位: 秒
     * @return
     */
    public static boolean getLock(String key, int timeout) {
        Jedis jedis = jedisPool.getResource();
        Long setnxResult = jedis.setnx(key, lockValue);
        if(null != setnxResult && 1L == setnxResult) {
            Long expireResult = jedis.expire(key, timeout);
            if(null != expireResult && 1L == expireResult) {
                return true;
            }
        }
        return false;
    }

    public static void unlock(String key) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(key);
    }


    /**
     * lua脚本方式加锁
     * @param key
     * @param expiretime 锁超时时间，单位：秒
     * @return
     */
    public static boolean getLuaLock(String key, int expiretime) {
        Jedis jedis = jedisPool.getResource();
        String res = jedis.set(key, lockValue, "NX", "EX", expiretime);
        return !StringUtils.isEmpty(res) && "OK".equals(res);
    }

    /**
     * 尝试获取lua锁
     * @param key
     * @param locktimeout 获取锁等待时间：秒
     * @param expiretime 锁超时时间，单位：秒
     * @return
     */
    public static boolean tryLuaLock(String key, int locktimeout, int expiretime)  {
        long start = System.currentTimeMillis();
        long end = start + locktimeout * 1000;
        boolean result = false;
        boolean lockResult = getLuaLock(key, expiretime);
        while(!lockResult) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
            }
            if(System.currentTimeMillis() > end) {
                break;
            }
            lockResult = getLuaLock(key, expiretime);
        }
        return lockResult;
    }

    /**
     * lua方式释放锁
     * @param key
     * @return
     */
    public static boolean releaseLuaLock(String key) {
        jedisPool.getResource().subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                super.onMessage(channel, message);
            }
        });

        Jedis jedis = jedisPool.getResource();
        Object res = jedis.eval(luaScript, Collections.singletonList(key), Collections.singletonList(lockValue));
        return null != res && res.equals("1L");
    }


}
