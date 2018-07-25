package com.kafka.demo.kafka.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

import java.util.List;

public interface KafkaConsumerMessage {

    /**
     * 消费消息
     * @param records
     * @param ack
     */
    public  abstract  void  getMessFromKafka(List<ConsumerRecord> records, Acknowledgment ack);
}
