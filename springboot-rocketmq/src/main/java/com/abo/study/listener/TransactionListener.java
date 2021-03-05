package com.abo.study.listener;

import com.abo.study.mapper.AccountInfoMapper;
import com.abo.study.service.AccountInfoService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lnb
 * @date 2021/3/4 11:14
 * @description：
 *
 * UNKNOW状态：表示事务消息未确定，可能是业务方执行本地事务逻辑时间耗时过长或者网络原因等引起的，该状态会导致broker对事务消息进行回查，
 * 默认回查总次数是15次，第一次回查间隔时间是6s，后续每次间隔60s,
 * ROLLBACK状态，该状态表示该事务消息被回滚，因为本地事务逻辑执行失败导致
 * COMMIT状态，表示事务消息被提交，会被正确分发给消费者。
 *
 */
@Component
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "producer_group_txmsg_bank1")
public class TransactionListener implements RocketMQLocalTransactionListener {

    @Autowired
    AccountInfoService accountInfoService;

    @Autowired
    AccountInfoMapper accountInfoDao;

    /**
     * 消息发送成功回调此方法，此方法执行本地事务
     */
    @Override
    @Transactional
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object arg) {
        //解析消息内容
        try {
            String jsonString = new String((byte[]) message.getPayload());
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            AccountChangeEvent accountChangeEvent = JSONObject.parseObject(jsonObject.getString("accountChange"), AccountChangeEvent.class);
            //扣除金额
            accountInfoService.doUpdateAccountBalance(accountChangeEvent);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            log.error("executeLocalTransaction 事务执行失败",e);
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 此方法检查事务执行状态
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        RocketMQLocalTransactionState state;
        final JSONObject jsonObject = JSON.parseObject(new String((byte[]) message.getPayload()));
        AccountChangeEvent accountChangeEvent = JSONObject.parseObject(jsonObject.getString("accountChange"),AccountChangeEvent.class);

        //事务id
        String txNo = accountChangeEvent.getTxNo();
        int isexistTx = accountInfoDao.isExistTx(txNo);
        log.info("回查事务，事务号: {} 结果: {}", accountChangeEvent.getTxNo(),isexistTx);
        if(isexistTx>0){
            state=  RocketMQLocalTransactionState.COMMIT;
        }else{
            state=  RocketMQLocalTransactionState.UNKNOWN;
        }

        return state;
    }
}
