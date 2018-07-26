package com.kafka.demo.entity;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class Worker  implements   Runnable {
    private String records;

    public Worker(String records) {
        this.records = records;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()
                           + "th message with offset: " + records);
    }
}
