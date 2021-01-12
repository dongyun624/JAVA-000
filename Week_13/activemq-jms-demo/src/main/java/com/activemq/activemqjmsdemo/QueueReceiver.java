package com.activemq.activemqjmsdemo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.annotation.PostConstruct;
import javax.jms.*;

/**
 * @author : dabing
 * @date : 2021/1/10
 */
public class QueueReceiver {


    // ConnectionFactory ：连接工厂，JMS 用它创建连接
    private static ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");


    public static void receiver() throws JMSException {

        // Connection ：JMS 客户端到JMS Provider 的连接
        final Connection connection =  connectionFactory.createConnection();

        connection.start();
        // Session： 一个发送或接收消息的线程
        final Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        // Destination ：消息的目的地;消息送谁那获取.
        Destination destination =  session.createQueue("my-queue");

        // 消费者，消息接收者
        MessageConsumer consumer1 =  session.createConsumer(destination);
        consumer1.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message msg) {
                try {
                    TextMessage message = (TextMessage)msg ;
                    System.out.println("queue consumer1 收到消息： " + message.getText());
                    session.commit();
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            }
        });

        MessageConsumer consumer2 =  session.createConsumer(destination);
        consumer1.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message msg) {
                try {
                    TextMessage message = (TextMessage)msg ;
                    System.out.println("queue consumer2 收到消息： " + message.getText());
                    session.commit();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });


        MessageConsumer consumer3 =  session.createConsumer(destination);
        consumer3.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message msg) {
                try {
                    TextMessage message = (TextMessage)msg ;
                    System.out.println("queue consumer3 收到消息： " + message.getText());
                    session.commit();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
