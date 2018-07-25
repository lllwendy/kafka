package com.kafka.demo.controller;

import com.kafka.demo.entity.Result;
import com.kafka.demo.kafka.KafkaProducer;
import com.kafka.demo.kafka.impl.KafkaMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    KafkaMessageSender kafkaMessageSender;


    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public Result WarnInfo(@RequestParam(required=true) String message){
        try {
            kafkaMessageSender.send(message);
        }catch (Exception  e) {
            e.printStackTrace();
        }
        return Result.ok();
    }
}
