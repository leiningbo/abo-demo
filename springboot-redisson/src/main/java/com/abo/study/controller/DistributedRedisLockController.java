package com.abo.study.controller;

import com.abo.study.utils.RedisUtils;
import io.swagger.annotations.Api;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Redisson分布式锁
 * @author lnb
 * @date 2021/2/24 11:41
 * @describe
 */
@RestController
@RequestMapping("/redis")
@Api
public class DistributedRedisLockController {

    private static final String STOCK_KEY = "stock";

    @Autowired
    private Redisson redisson;
    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping(value = "/setDefaultStock")
    public String setDefaultStock() {
        String stockNumber = "200";
        redisUtils.setEx(STOCK_KEY, stockNumber, 1, TimeUnit.HOURS);
        return "初始化库存：" + redisUtils.get(STOCK_KEY);
    }

    /**
     * 减库存
     */
    @RequestMapping( value = "/reduceStock", method = RequestMethod.POST )
    public String reduceStock() {
        RLock lock = redisson.getLock("/payOrder");
        try {
            lock.lock();
            int stock = Integer.parseInt(redisUtils.get(STOCK_KEY));
            if (stock > 0) {
                int reduceStock = stock - 1;
                redisUtils.setEx(STOCK_KEY, reduceStock + "", 1, TimeUnit.HOURS);
                System.out.println("减库成功，剩余库存：" + reduceStock);
            } else {
                System.out.println("减库失败，库存不足");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return "支付成功";
    }
}
