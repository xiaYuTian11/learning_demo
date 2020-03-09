package com.demo;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 生产者
 *
 * @author TMW
 * @since 2020/3/9 13:49
 */
public class Produce {

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
            // 创建会话 不需要事务
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建topic
            Topic sessionTopic = session.createTopic("ac_test_1");

            MessageProducer producer = session.createProducer(sessionTopic);
            for (int i = 0; i < 10; i++) {
                producer.send(sessionTopic, session.createTextMessage("send..."));
            }

            // session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
