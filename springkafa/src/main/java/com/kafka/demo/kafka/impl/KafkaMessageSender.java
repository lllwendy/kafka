package com.kafka.demo.kafka.impl;
import org.springframework.kafka.core.KafkaTemplate;

public interface KafkaMessageSender {
    /**
     * 发送消息
     * @param topic  消息主题
     * @param message  消息内容
     */
    public  abstract   void  sendMessToKafka(String topic,String message);
}
