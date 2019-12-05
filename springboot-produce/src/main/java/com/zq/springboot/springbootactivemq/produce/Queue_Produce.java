package com.zq.springboot.springbootactivemq.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

/**
 * @ClassNameQueue_Produce
 * @Description
 * @Author
 * @Date2019/12/5 17:17
 * @Version V1.0
 **/
@Component
public class Queue_Produce {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;
    public void produceMsg(){
        jmsMessagingTemplate.convertAndSend(queue,"****"+ UUID.randomUUID().toString().substring(0,6));
    }
    @Scheduled(fixedDelay = 3000)
    public void produceMsgScheduled(){
        jmsMessagingTemplate.convertAndSend(queue,"****Scheduled"+ UUID.randomUUID().toString().substring(0,6));
        System.out.println("**********produceMsgScheduled send ok");
    }
}
