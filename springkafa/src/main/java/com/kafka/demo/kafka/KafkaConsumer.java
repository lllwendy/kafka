package com.kafka.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author  javawendy(lll)
 * @create  2018/7/20 12:47
 * @desc 消费者从zk的borker上获取数据
 **/
@Component
public class KafkaConsumer {

    public static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = {"jktopic"})
    public  void getMessFromKafka(ConsumerRecord consumerRecord) throws InterruptedException {
        System.out.println(consumerRecord.offset());
        System.out.println(consumerRecord.value().toString());
        Thread.sleep(3000);
    }
}