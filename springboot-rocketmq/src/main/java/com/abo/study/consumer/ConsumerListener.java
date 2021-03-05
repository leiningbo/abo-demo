package com.abo.study.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author lnb
 * @date 2021/3/4 11:18
 * @description
 *
 * 消费模式：有序消费和并发消费。
 * 有序消费模式是按照消息的顺序进行消费，并发消费的消费速度要比有序消费更快。
 *
 * 消息模式： Clustering 和Broadcasting
 * CLUSTERING：同组里的每个Consumer 只消费所订阅消息的一部分内容。
 * BROADCASTING：同组里的每个Consumer 消费所订阅消息的全部内容
 *
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "tx-topic-1",consumerGroup = "c-group",
        consumeMode = ConsumeMode.CONCURRENTLY, messageModel = MessageModel.CLUSTERING)
public class ConsumerListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        log.info("received message :{}", s);
    }
}
