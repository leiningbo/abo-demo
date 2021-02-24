package com.abo.study.springbootredisson.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lnb
 * @date 2021/2/24 11:21
 * @describe redisson分布式锁
 */
@Configuration
public class RedissonConfig {

    @Value( "${spring.redis.host}" )
    private String host;
    @Value("${spring.redis.port}")
    private String port;
    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public Redisson redisson() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port).setPassword(password).setDatabase(0);
        return (Redisson) Redisson.create(config);
    }

}
