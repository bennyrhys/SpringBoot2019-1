package com.example.demo.listen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class HelloSpringApplicationRunListener implements SpringApplicationRunListener {

    public HelloSpringApplicationRunListener(SpringApplication application, String[] args){
        
    }

    @Override
    public void starting() {
        System.out.printf("SpringApplicationRunListener。。。。。。。starting");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        Object o = environment.getSystemProperties().get("os.name");
        System.out.printf("SpringApplicationRunListener.........environmentPrepared。。。。。。系统属性"+o);
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.printf("SpringApplicationRunListener...........contextPrepared");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.printf("SpringApplicationRunListener.......contextLoaded");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.printf("SpringApplicationRunListener.......started");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.printf("SpringApplicationRunListener..........running");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.printf("SpringApplicationRunListener.........failed");
    }
}
