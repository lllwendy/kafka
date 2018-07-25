package com.kafka.demo.kafka;


import com.kafka.demo.entity.Worker;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.kafka.demo.kafka.impl.KafkaMessageConsumer;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author  javawendy(lll)
 * @create  2018/7/20 12:47
 * @desc 消费者从zk的borker上获取数据
 **/
@Component
public class KafkaConsumer implements KafkaMessageConsumer {

    public static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    private ExecutorService executors;

    @Override
    @KafkaListener(topics = {"${kafka.topic.foo}"})
    public  void  receive(ConsumerRecord<Integer, String> message, Acknowledgment ack, int threads) {
        try {
            executors = new ThreadPoolExecutor(threads, threads, 0L, TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue(1000), new ThreadPoolExecutor.CallerRunsPolicy());

            logger.info("消费监听："+message.value());
            executors.submit(new Worker(message));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 手动提交偏移量
            ack.acknowledge();
        }
    }

    @Override
    public void shutdown() {
        if (executors != null) {
            executors.shutdown();
        }
        try {
            if (!executors.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("Timeout.... Ignore for this case");
            }
        } catch (InterruptedException ignored) {
            System.out.println("Other thread interrupted this shutdown, ignore for this case.");
            Thread.currentThread().interrupt();
        }
    }
}