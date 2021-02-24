package com.abo.study.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lnb
 * @date 2021/2/24 11:29
 * @describe
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Value("${system.swagger.host}")
    private String host;

    /**
     * 生成环境下记得设置为false
     */
    @Value("${system.swagger.enable}")
    private Boolean enable;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .host(host)
                .apiInfo(apiInfo())
                .select()
                // 暴露接口地址的包路径
                .apis(RequestHandlerSelectors.basePackage("com.abo.study"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("")
                .description("")
                .termsOfServiceUrl("")
                .version("")
                .build();
    }

}
