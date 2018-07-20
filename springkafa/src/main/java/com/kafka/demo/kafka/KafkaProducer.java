package com.kafka.demo.kafka;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author  javawendy(lll)
 * @create  2018/7/20 12:47
 * @desc
 **/

@Component
public class KafkaProducer {
    @Autowired
    KafkaTemplate kafkaTemplate;

    public void sendMessToKafka() throws Exception {
        User userInfo=new User();
        userInfo.setUserName("lll");
        userInfo.setUserPassword("123");
        ListenableFuture send = kafkaTemplate.send("jktopic", "key", JSON.toJSONString(userInfo));
    }
}
