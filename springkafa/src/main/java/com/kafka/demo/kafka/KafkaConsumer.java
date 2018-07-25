package com.kafka.demo.kafka;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.kafka.demo.kafka.impl.KafkaMessageConsumer;

/**
 * @author  javawendy(lll)
 * @create  2018/7/20 12:47
 * @desc 消费者从zk的borker上获取数据
 **/
@Component
public class KafkaConsumer implements KafkaMessageConsumer {

    public static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Override
    @KafkaListener(topics = {"${kafka.topic.foo}"})
    public  void  receive(ConsumerRecord<Integer, String> message,Acknowledgment ack) {
        try {
            logger.info("消费监听："+message.value());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 手动提交偏移量
            ack.acknowledge();
        }
    }
}