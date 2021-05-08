package com.abo.study;


import com.abo.study.entity.DeepCloneEntity;
import com.abo.study.entity.DemoEntity;
import com.abo.study.entity.TradeUser;
import com.abo.study.service.TradeUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith( SpringRunner.class )
@SpringBootTest
public class SpringbootMybatisplusApplicationTests {

    @Resource
    private TradeUserService tradeUserService;

    @Test
    public void test() {
        List<TradeUser> tradeUsers = tradeUserService.list();
        for (TradeUser tradeUser : tradeUsers) {
            System.out.println(tradeUser);
        }
    }


    @Test
    public void test1() throws CloneNotSupportedException {
        DemoEntity demoEntity = new DemoEntity();
        DeepCloneEntity entity = new DeepCloneEntity();
        DeepCloneEntity clone = (DeepCloneEntity) entity.clone();


    }


}
