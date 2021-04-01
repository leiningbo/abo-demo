package com.abo.study;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class SpringbootRedissonApplicationTests {

    @Test
    public void test() {
        Integer a = Integer.valueOf(130);
        Integer b = 130;

        System.out.println(a == b);

        int a1 = 130;
        Integer b1 = 130;
        System.out.println(a1 == b1);

        Integer a2 = 130;
        Integer b2 = 130;
        System.out.println(a2 == b2);

        Integer a3 = Integer.valueOf(130);
        Integer b3 = Integer.valueOf(130);
        System.out.println(a3 == b3);


    }

}
