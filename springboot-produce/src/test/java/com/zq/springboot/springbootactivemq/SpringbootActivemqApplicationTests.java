package com.zq.springboot.springbootactivemq;

import com.zq.springboot.springbootactivemq.produce.Queue_Produce;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringbootActivemqApplication.class)

class SpringbootActivemqApplicationTests {
    @Autowired
private Queue_Produce queueProduce;
    @Test
    void contextLoads() {
        queueProduce.produceMsg();
    }

}
