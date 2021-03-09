package com.abo.study.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author lnb
 * @date 2021/3/9 15:49
 * @description
 */
@Slf4j
@Service
public class CuratorDisLockOrderServiceImpl {

    private static final String LOCK_PATH = "/distribute-lock";

    @Autowired
    private CuratorFramework curatorFramework;


    public String createOrder() {
        String orderSn = "";
        InterProcessMutex lock = new InterProcessMutex(curatorFramework, LOCK_PATH);
        try {
            lock.acquire();
            orderSn = UUID.randomUUID().toString();
            log.info(Thread.currentThread().getName() + "-->获取锁成功-->生成订单编号:{}", orderSn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                lock.release();
                log.info(Thread.currentThread().getName() + "-->释放锁成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderSn;
    }

}
