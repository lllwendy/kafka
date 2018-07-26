package com.kafka.demo.kafka.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;


public interface KafkaMessageConsumer {

    /**
     * 消费消息
     * @param message
     */
    public  abstract  void  receive(String message, Acknowledgment ack, int threads);

    public  abstract  void shutdown();
}
