package com.bennyrhys.starters.bennyrhysspringbootstartersautoconfigurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication//web应用
@EnableConfigurationProperties(HelloProperties.class)//使service拿到properties
public class HelloAutoConfig {
    @Autowired
    HelloProperties helloProperties;

    //给容器中加了helloservice别人想用就能用了
    @Bean
    public HelloService helloService(){
        HelloService helloService = new HelloService();
        helloService.setHelloProperties(helloProperties );
        return helloService;
    }
}
