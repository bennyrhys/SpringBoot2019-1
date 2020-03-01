package com.bennyrhys.springboot04restfulcurd.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.printf("contextInitialized……web应用启动");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.printf("contextDestroyed……web销毁");
    }
}
