package com.pratise.redislock;

import com.pratise.redislock.lock.RedisLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class RedisLockApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisLockApplication.class, args);
	}

    private static String INVENTORY_REMAIN_KEY = "inventory:remain:%s";
    private static String INVENTORY_LOCK_KEY = "inventory:%s";

    public static void decInventory(Integer productId, int buyCount) {
        String key = String.format(INVENTORY_REMAIN_KEY, productId);
        String lockKey = String.format(INVENTORY_LOCK_KEY, productId);
        Integer count = Integer.valueOf(key);
        boolean lock = RedisLock.tryLuaLock(lockKey, 300, 100);
        if(!lock) {
            throw new RuntimeException("获取锁失败");
        }

        try {
            if(count < buyCount) {
                throw new RuntimeException("库存不足");
            }

            long remainCount = RedisLock.decrBy(key, buyCount);
            System.out.println("剩余库存: " + remainCount);
        } finally {
            RedisLock.releaseLuaLock(lockKey);
        }


    }

}
