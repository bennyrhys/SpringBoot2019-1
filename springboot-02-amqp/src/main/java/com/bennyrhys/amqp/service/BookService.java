package com.bennyrhys.amqp.service;

import com.bennyrhys.amqp.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    //监听消息
    @RabbitListener(queues = "bennyrhys.news")
    public void receive(Book book){
        System.out.println("收到消息："+book);
    }

    //监听消息头
    @RabbitListener(queues = "bennyrhys")
    public void receiveHeader(Message message){
        byte[] body = message.getBody();
        MessageProperties messageProperties = message.getMessageProperties();
        System.out.println("这是消息体："+body);
        System.out.println("这是消息头："+messageProperties);

    }
}

