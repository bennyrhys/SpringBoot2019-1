package com.bennyrhys.task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService  {
    //告诉spring这是一个异步方法 ：spring开启一个线程池开始调用
    //要想起作用 主函数 enable注解
    @Async
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("数据处理中。。。。。");
    }
}
