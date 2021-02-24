package com.abo.study;

import com.abo.study.config.ZookeeperConfig;
import com.abo.study.thread.TestThread;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith( SpringRunner.class )
@SpringBootTest
public class SpringbootZookeeperApplicationTests {

    @Autowired
    private CuratorFramework curatorFramework;

    @Test
    public void contextLoads() {
        curatorFramework.start();
        InterProcessMutex lock = new InterProcessMutex(curatorFramework,"/aboLock");
        for (int i = 0; i < 50; i++) {
            new Thread(new TestThread(i, lock)).start();
        }
    }



}
