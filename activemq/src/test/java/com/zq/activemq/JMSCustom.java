package com.zq.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @ClassNameJMSCustom
 * @Description
 * @Author
 * @Date2019/11/28 19:46
 * @Version V1.0
 **/
public class JMSCustom {
    public static final String ACTIVEMQ_URL = "tcp://192.168.35.157:61616";
    public static final String QUEUE_NAME = "queue01";
    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("我是1号消费者。。。。");
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
        //创建消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);
       /* while (true){
            //消费者接受队列消息
            TextMessage textMessage = (TextMessage)messageConsumer.receive();
            if(null != textMessage){
                System.out.println(textMessage.getText());
            }else{
                break;
            }

        }*/
       //使用消息监听
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(null != message || message instanceof  TextMessage){
                    TextMessage textMessage = (TextMessage)message;
                    try {
                        String co1 = textMessage.getStringProperty("co1");
                        System.out.println("co1======="+co1);
                        System.out.println("****消费者的消息："+textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();

    }

}
