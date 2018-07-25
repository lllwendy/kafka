package com.kafka.demo.kafka.impl;


public interface KafkaMessageSender {
    /**
     * 发送消息
     * @param message  消息内容
     */
    public  abstract   void  send(String message);
}
