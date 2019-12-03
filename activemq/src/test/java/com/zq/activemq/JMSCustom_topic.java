package com.zq.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @ClassNameJMSCustom_topic
 * @Description
 * @Author
 * @Date2019/11/29 10:46
 * @Version V1.0
 **/
public class JMSCustom_topic {
    public static final String ACTIVEMQ_URL = "tcp://192.168.35.157:61616";
    public static final String TOPIC_NAME = "ZQ_TOPIC";
    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("我是5号消费者。。。。");
        // 1 按照给定的url创建连接工程，这个构造器采用默认的用户名密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2 通过连接工厂连接 connection  和 启动
        javax.jms.Connection connection = activeMQConnectionFactory.createConnection();
        connection.setClientID("z4");
        //  启动
        connection.start();
        // 3 创建回话  session
        // 两个参数，第一个事务， 第二个签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4 创建目的地 （两种 ： 队列/主题   这里用主题）
        Topic topic = session.createTopic(TOPIC_NAME);
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic,"remark");
        connection.start();
        Message message = topicSubscriber.receive();
        while (null != message){
            TextMessage textMessage  = (TextMessage)message;
            System.out.println("*****收到持久化的topic"+textMessage.getText());
            message = topicSubscriber.receive();
        }
        session.close();
        connection.close();

    }
}
