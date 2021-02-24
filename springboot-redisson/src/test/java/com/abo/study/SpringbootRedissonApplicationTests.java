package com.abo.study;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith( SpringRunner.class )
@SpringBootTest
public class SpringbootRedissonApplicationTests {

    @Test
    public void test() {
        int a = 1;
        int b = 2;
        System.out.println(a + b);
    }

}
