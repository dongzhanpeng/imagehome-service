package com.fly.imagehome.service.mq.impl;

import com.fly.imagehome.rocketmq.topicenum.TopicEnum;
import com.fly.imagehome.service.mq.RocketMqService;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RocketMqServiceImpl implements RocketMqService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public SendResult send(String topic, String message) {
        return rocketMQTemplate.syncSend(TopicEnum.IMAGE_HOME_TEST_TOPIC.getCode(), message);

    }

    @Override
    public SendResult send(String topic, String message, Integer delayTimeLevel) {
        return rocketMQTemplate.syncSend(TopicEnum.IMAGE_HOME_TEST_TOPIC.getCode(), message, delayTimeLevel);
    }
}
