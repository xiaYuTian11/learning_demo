package com.demo;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.util.ThreadPoolUtils;

import javax.jms.*;

/**
 * @author TMW
 * @since 2020/3/9 13:58
 */
public class Consumer {

    /**
     * 默认用户名
     */
    public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    /**
     * 默认密码
     */
    public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    /**
     * 默认连接地址
     */
    public static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
        try {
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic sessionTopic = session.createTopic("ac_test_1");
            MessageConsumer consumer = session.createConsumer(sessionTopic);
            consumer.setMessageListener((message) -> {
                try {
                    System.out.println(("consumer1 ... 接收消息" + ((TextMessage) message).getText()));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });

            consumer.setMessageListener((message) -> {
                try {
                    System.out.println(("consumer2 ... 接收消息" + ((TextMessage) message).getText()));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });

            consumer.setMessageListener((message) -> {
                try {
                    System.out.println(("consumer2 ... 接收消息" + ((TextMessage) message).getText()));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });

            Thread.sleep(1000 * 1000);

            session.close();
            connection.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
