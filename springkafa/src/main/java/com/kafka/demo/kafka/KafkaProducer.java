package com.kafka.demo.kafka;

import com.alibaba.fastjson.JSON;
import com.kafka.demo.controller.KafkaController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author  javawendy(lll)
 * @create  2018/7/20 12:47
 * @desc  生产者发送数据到borker
 **/

@Component
public class KafkaProducer {
    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    KafkaTemplate kafkaTemplate;

    public void sendMessToKafka() throws Exception {
        User userInfo=new User();
        userInfo.setUserName("lll3");
        userInfo.setUserPassword("1234");
        ListenableFuture send = kafkaTemplate.send("jktopic", "key", JSON.toJSONString(userInfo));
        logger.info("发送消息成功："+JSON.toJSONString(userInfo));
    }
}
