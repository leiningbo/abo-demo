package com.abo.study.thread;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.stereotype.Component;

/**
 * @author lnb
 * @date 2021/2/24 15:26
 * @description
 */
@Slf4j
public class TestThread implements Runnable {

    private Integer threadFlag;

    private InterProcessMutex lock;

    public TestThread(Integer threadFlag, InterProcessMutex lock) {
        this.threadFlag = threadFlag;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            System.out.println("第" + threadFlag + "线程获取锁xxxxxx");
            lock.acquire();
//            log.info("第" + threadFlag + "线程获取锁");
            System.out.println("第" + threadFlag + "线程获取锁");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
