package com.zq.springboot.springbootcustom.custom;

import org.omg.PortableInterceptor.DISCARDING;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @ClassNameQueue_Custom
 * @Description
 * @Author
 * @Date2019/12/5 17:48
 * @Version V1.0
 **/
@Component
public class Queue_Custom {
    @JmsListener(destination="${myqueue}")
    public void receive(TextMessage message) throws JMSException {
        System.out.println("********消费者收到消息"+message.getText());
    }
}
