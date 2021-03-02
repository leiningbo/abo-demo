package com.abo.study;



import com.abo.study.mapper.TradeUserMapper;
import com.abo.study.entity.TradeUser;
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
    private TradeUserMapper userMapper;

    @Test
    public void test() {
        List<TradeUser> tradeUsers = userMapper.list();
        for (TradeUser tradeUser : tradeUsers) {
            System.out.println(tradeUser);
        }
    }

}
