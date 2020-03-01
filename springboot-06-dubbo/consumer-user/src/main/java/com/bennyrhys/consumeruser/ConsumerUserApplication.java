package com.bennyrhys.consumeruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *     1、引入dubbo和zookeeper相关依赖
 *     2、配置dubbo的扫描包，和注册中心地址
 *     3、使用@service发布服务
 */
@SpringBootApplication
public class ConsumerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerUserApplication.class, args);
    }

}
