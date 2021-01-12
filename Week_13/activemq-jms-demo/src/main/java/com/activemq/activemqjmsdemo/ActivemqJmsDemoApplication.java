package com.activemq.activemqjmsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.jms.JMSException;

@SpringBootApplication
public class ActivemqJmsDemoApplication {

	public static void main(String[] args) throws InterruptedException, JMSException {
		SpringApplication.run(ActivemqJmsDemoApplication.class, args);

        new Thread(() -> {
            try {
                TopicReceiver.receiver();
//                QueueReceiver.receiver();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }).start();


//		for(int i = 0;i<100;i++) {
//            QueueSender.send("第" + i + "个queue message");
//        }

        for(int i = 0;i<100;i++) {
            TopicSender.send("第" + i + "个queue message");
        }
	}

}
