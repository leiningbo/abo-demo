package com.abo.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootRedissonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedissonApplication.class, args);
    }

}
