package com.kafka.demo;

import com.alibaba.fastjson.JSON;
import com.kafka.demo.entity.User;
import com.kafka.demo.kafka.KafkaConsumer;
import com.kafka.demo.kafka.KafkaProducer;
import com.kafka.demo.kafka.impl.KafkaMessageSender;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * @author  javawendy(lll)
 * @create  2018/7/20 12:47
 * @desc
 **/


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	KafkaMessageSender  kafkaMessageSender;

	@Test
	public void send () {
		try {
			User  user=new  User();
			user.setUserName("sss2");
			user.setUserPassword("1234567");
			kafkaMessageSender.send(JSON.toJSONString(user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
