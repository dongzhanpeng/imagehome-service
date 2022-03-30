package com.fly.imagehome.service.mq;

import org.apache.rocketmq.client.producer.SendResult;

public interface RocketMqService {
	
	public SendResult send(String topic, String message);
	
	public SendResult send(String topic, String message, Integer delayTimeLevel);
	
}
