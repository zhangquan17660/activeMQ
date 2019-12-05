package com.zq.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.TextMessage;

/**
 * @ClassNametest
 * @Description
 * @Author
 * @Date2019/12/5 14:53
 * @Version V1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:springmvc.xml"})
public class test {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Test
    public void testActiveMQ_produce(){
        jmsTemplate.send((session) -> {
            TextMessage textMessage = session.createTextMessage("spring 和 activemq 的整合");
            return textMessage;
        });
        System.out.println(" *** send task over ***");
    }

    @Test
    public void testActiveMQ_custom(){
        String returnmessage = (String)jmsTemplate.receiveAndConvert();
        System.out.println("******消费者消息******"+returnmessage);
    }
}
