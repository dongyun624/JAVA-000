package com.kafka.pratise.kafkademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KafkaDemoApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(KafkaDemoApplication.class, args);


        KafkaSender kafkaSender = applicationContext.getBean(KafkaSender.class);
        for (int i=0;i<100;i++) {
            kafkaSender.send(i);
        }

	}

}
