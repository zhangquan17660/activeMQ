package com.zq.mvc.mq;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @ClassNameMyMessageListener
 * @Description
 * @Author
 * @Date2019/12/5 16:45
 * @Version V1.0
 **/
@Component
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if(null != message || message instanceof TextMessage){
            TextMessage textMessage = (TextMessage)message;
            try {
                System.out.println("****消费者的消息："+textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
