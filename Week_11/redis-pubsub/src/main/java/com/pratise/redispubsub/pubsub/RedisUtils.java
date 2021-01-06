package com.pratise.redispubsub.pubsub;

import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * @author : dabing
 * @date : 2021/1/6
 */
public class RedisUtils {

    private static JedisPool jedisPool = new JedisPool();

    /**
     * 发布消息
     * @param channel
     * @param message
     */
    public static void publist(String channel, String message) {
        if(!StringUtils.hasLength(channel) || !StringUtils.hasLength(message)) {
            return;
        }
        jedisPool.getResource().publish(channel, message);
    }

    /**
     * 接受消息
     * @param channel
     */
    public static void subscribe(String channel) {
        jedisPool.getResource().subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("subscribe: " + message);
                super.onMessage(channel, message);
            }
        }, channel);
    }


}
