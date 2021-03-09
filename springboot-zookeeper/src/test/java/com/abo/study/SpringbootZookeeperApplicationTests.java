package com.abo.study;

import com.abo.study.service.CuratorDisLockOrderServiceImpl;
import com.abo.study.thread.TestThread;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@RunWith( SpringRunner.class )
@SpringBootTest
@Slf4j
public class SpringbootZookeeperApplicationTests {

    @Autowired
    private CuratorFramework curatorFramework;

    @Autowired
    private CuratorDisLockOrderServiceImpl curatorDisLockOrderService;

    @Test
    public void contextLoads() {
        curatorFramework.start();
        InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/aboLock");
        for (int i = 0; i < 50; i++) {
            new Thread(new TestThread(i, lock)).start();
        }
    }

    @Test
    public void test() {
        int count = 20;
        // 循环屏障
        CyclicBarrier cb = new CyclicBarrier(count);
        for (int i = 0; i < count; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    log.info(Thread.currentThread().getName() + "--准备好");
                    try {
                        cb.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    curatorDisLockOrderService.createOrder();
                }

            }).start();
        }
    }





}
