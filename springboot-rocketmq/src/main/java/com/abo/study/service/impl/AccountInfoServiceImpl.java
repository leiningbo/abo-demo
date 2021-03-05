package com.abo.study.service.impl;

import com.abo.study.mapper.AccountInfoMapper;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author lnb
 * @date 2021/3/4 16:16
 * @description
 */
@Service
@Slf4j
public class AccountInfoServiceImpl {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    /**
     * 更新帐号余额-发送消息
     * producer向MQ Server发送消息
     *
     * @param accountChangeEvent
     */
    @Override
    public void sendUpdateAccountBalance(AccountChangeEvent accountChangeEvent) {
        //构建消息体
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accountChange",accountChangeEvent);
        Message<String> message = MessageBuilder.withPayload(jsonObject.toJSONString()).build();
        TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction("producer_group_txmsg_bank1", "topic_txmsg", message, null);

        log.info("send transcation message body={},result={}",message.getPayload(),sendResult.getSendStatus());
    }

    /**
     * 更新帐号余额-本地事务
     * producer发送消息完成后接收到MQ Server的回应即开始执行本地事务
     *
     * @param accountChangeEvent
     */
    @Transactional
    @Override
    public void doUpdateAccountBalance(AccountChangeEvent accountChangeEvent) {
        log.info("开始更新本地事务，事务号：{}",accountChangeEvent.getTxNo());
        accountInfoMapper.updateAccountBalance(accountChangeEvent.getAccountNo(),accountChangeEvent.getAmount() * -1);
        //为幂等作准备
        accountInfoMapper.addTx(accountChangeEvent.getTxNo());
        if(accountChangeEvent.getAmount() == 2){
            throw new RuntimeException("bank1更新本地事务时抛出异常");
        }
        log.info("结束更新本地事务，事务号：{}",accountChangeEvent.getTxNo());
    }



}
