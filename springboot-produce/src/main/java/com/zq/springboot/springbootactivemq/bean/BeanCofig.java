package com.zq.springboot.springbootactivemq.bean;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @ClassNameBeanCofig
 * @Description
 * @Author
 * @Date2019/12/5 17:13
 * @Version V1.0
 **/
@Configuration
@EnableJms
public class BeanCofig {
    @Value("${myqueue}")
    private String myqueue;
    @Bean
    public Queue queue(){
        return new ActiveMQQueue(myqueue);
    }
}
