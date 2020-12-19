package com.yinrj.business;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * @author Yin
 * @date 2020/12/19
 */
@ComponentScan("com.yinrj")
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.yinrj.server.mapper")
public class BusinessApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessApplication.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BusinessApplication.class);
        Environment environment = app.run(args).getEnvironment();
        LOGGER.info("启动成功");
        LOGGER.info("Business地址: \thttp://127.0.0.1:{}", environment.getProperty("server.port"));
    }
}
