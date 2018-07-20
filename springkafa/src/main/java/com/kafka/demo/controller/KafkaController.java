package com.kafka.demo.controller;

import com.kafka.demo.kafka.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author  javawendy(lll)
 * @create  2018/7/20 12:47
 * @desc
 **/

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private Logger logger = LoggerFactory.getLogger(KafkaController.class);

    private static int corePoolSize = Runtime.getRuntime().availableProcessors();
    //调整队列数 拒绝服务
    private static ThreadPoolExecutor executor  = new ThreadPoolExecutor(corePoolSize, corePoolSize+1, 10l, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(10000));

    @Autowired
    KafkaProducer kafkaProducer;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public void WarnInfo() throws Exception {
        int count=10;
        for (int i = 0; i < count; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    try {
                        kafkaProducer.sendMessToKafka();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            executor.execute(task);
        }
        try {
            Thread.sleep(10000);
            logger.info("一共秒杀了多少数据....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
