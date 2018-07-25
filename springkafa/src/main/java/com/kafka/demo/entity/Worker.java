package com.kafka.demo.entity;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class Worker  implements   Runnable {
    private    ConsumerRecord<Integer,String>    records;

    public Worker(ConsumerRecord<Integer, String> records) {
        this.records = records;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " consumed " + records.partition()
                           + "th message with offset: " + records.offset());
    }
}
