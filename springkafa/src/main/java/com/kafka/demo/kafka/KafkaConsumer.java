package com.kafka.demo.kafka;

import com.kafka.demo.kafka.impl.KafkaConsumerMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author  javawendy(lll)
 * @create  2018/7/20 12:47
 * @desc 消费者从zk的borker上获取数据
 **/
@Component
public class KafkaConsumer implements KafkaConsumerMessage {

    public static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Override
    @KafkaListener(topics = {"juntopic"})
    public void getMessFromKafka(List<ConsumerRecord> records, Acknowledgment ack) {
        try {
            for (ConsumerRecord record : records) {
                logger.info("消费监听："+record.value());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ack.acknowledge();//手动提交偏移量offset
        }
    }
}