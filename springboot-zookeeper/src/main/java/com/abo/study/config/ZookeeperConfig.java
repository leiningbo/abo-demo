package com.abo.study.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lnb
 * @date 2021/2/24 15:09
 * @description
 */
@Configuration
public class ZookeeperConfig {

    @Value("${curator.retryCount}")
    private int retryCount;

    @Value("${curator.elapsedTimeMs}")
    private int elapsedTimeMs;

    @Value("${curator.url}")
    private String url;

    @Value("${curator.sessionTimeout}")
    private int sessionTimeout;

    @Value("${curator.connectionTimeout}")
    private int connectionTimeout;

    @Bean
    public CuratorFramework getCuratorFramework() {
        return CuratorFrameworkFactory.builder()
                .connectString(url)
                .sessionTimeoutMs(sessionTimeout)
                .connectionTimeoutMs(connectionTimeout)
                .retryPolicy(new ExponentialBackoffRetry(retryCount, elapsedTimeMs)).build();
    }


}
