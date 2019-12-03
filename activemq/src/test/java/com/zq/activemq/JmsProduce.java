package com.zq.activemq;


import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;


/**
 * Unit test for simple App.
 */
public class JmsProduce
{
    //  linux 上部署的activemq 的 IP 地址 + activemq 的端口号
    public static final String ACTIVEMQ_URL = "tcp://192.168.35.157:61616";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws  Exception{

        // 1 按照给定的url创建连接工程，这个构造器采用默认的用户名密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2 通过连接工厂连接 connection  和 启动
        javax.jms.Connection connection = activeMQConnectionFactory.createConnection();
        //  启动
        connection.start();
        // 3 创建回话  session
        // 两个参数，第一个事务， 第二个签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4 创建目的地 （两种 ： 队列/主题   这里用队列）
        Queue queue = session.createQueue(QUEUE_NAME);
        // 5 创建消息的生产者
        MessageProducer messageProducer = session.createProducer(queue);
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        // 6 通过messageProducer 生产 3 条 消息发送到消息队列中
        for (int i = 1; i < 4 ; i++) {
            // 7  创建字消息
            TextMessage textMessage = session.createTextMessage("msg--" + i);
            // 8  通过messageProducer发布消息
            textMessage.setStringProperty("co1","vip");
            messageProducer.send(textMessage);
        }
        // 9 关闭资源
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("  **** 消息发送到MQ完成 ****");
    }
}
