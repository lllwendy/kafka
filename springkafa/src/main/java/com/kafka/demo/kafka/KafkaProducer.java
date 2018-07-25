package com.kafka.demo.kafka;

import com.alibaba.fastjson.JSON;
import com.kafka.demo.entity.Result;
import com.kafka.demo.kafka.impl.KafkaMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;

import java.util.HashMap;

/**
 * @author  javawendy(lll)
 * @create  2018/7/20 12:47
 * @desc  生产者发送数据到borker
 **/

@Component
public class KafkaProducer implements KafkaMessageSender{
    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Override
    public void sendMessToKafka(String topic, String message) {
        ListenableFuture future=kafkaTemplate.send(topic,"key",message);
        //发送成功后回调，确认消息成功发送
        SuccessCallback successCallback = new SuccessCallback() {
            @Override
            public void onSuccess(Object result) {
                System.out.println("发送成功");
                // UUID.randomUUID();//为每条数据生成标识存在数据库，不会重复
            }
        };
        //发送失败回调
        FailureCallback failureCallback = new FailureCallback() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("发送失败");
            }
        };
        future.addCallback(successCallback,failureCallback);
    }
}