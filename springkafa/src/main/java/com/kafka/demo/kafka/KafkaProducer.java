package com.kafka.demo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import com.kafka.demo.kafka.impl.KafkaMessageSender;
/**
 * @author  javawendy(lll)
 * @create  2018/7/20 12:47
 * @desc  生产者发送数据到borker
 **/

@Component
public class KafkaProducer implements KafkaMessageSender{
    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${kafka.topic.foo}")
    private String topic;

    @Override
    public void send(String message) {
        ListenableFuture<SendResult<String, String>> future=kafkaTemplate.send(topic,message);
        future.addCallback(success -> logger.info("KafkaMessageProducer 发送消息成功！"+message),
                fail -> logger.error("KafkaMessageProducer 发送消息失败！"));
    }
}