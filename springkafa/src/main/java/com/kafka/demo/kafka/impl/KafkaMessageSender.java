package com.kafka.demo.kafka.impl;
import org.springframework.kafka.core.KafkaTemplate;

public interface KafkaMessageSender {
    /**
     * 发送消息
     * @param message  消息内容
     */
    public  abstract   void  send(String message);
}
