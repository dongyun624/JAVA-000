package com.pratise.redispubsub;

import com.pratise.redispubsub.pubsub.RedisUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisPubsubApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisPubsubApplication.class, args);

        new Thread(() -> RedisUtils.subscribe("myChannel")).start();

        RedisUtils.publist("myChannel", "你好");



        RedisUtils.publist("myChannel", "你好, 美女");
        RedisUtils.publist("myChannel", "aa");


    }

}
