package com.activemq.activemqjmsdemo;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author : dabing
 * @date : 2021/1/10
 */
public class TopicSender {

    // ConnectionFactory ：连接工厂，JMS 用它创建连接
    private static ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
            "admin",
            "admin",
            "tcp://localhost:61616");



    public static void send(String message) throws JMSException, InterruptedException {
        // Connection ：JMS 客户端到JMS Provider 的连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // Session： 一个发送或接收消息的线程
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        // Destination ：消息的目的地;消息发送给谁.
        Destination destination = session.createTopic("my-topic");

        // MessageProducer：消息发送者
        MessageProducer producer = session.createProducer(destination);

        // 设置不持久化，可以更改
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        //创建文本消息
        TextMessage textMessage = session.createTextMessage(message);
        //发送消息
        producer.send(textMessage);

        session.commit();
        session.close();
        connection.close();
    }


}
