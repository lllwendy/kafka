package com.kafka.demo.kafka.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;

import java.util.List;

public interface KafkaMessageConsumer {

    /**
     * 消费消息
     * @param message
     */
    public  abstract  void  receive(ConsumerRecord<Integer, String> message, Acknowledgment ack);
}
