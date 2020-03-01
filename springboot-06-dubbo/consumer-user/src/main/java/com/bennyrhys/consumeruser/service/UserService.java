package com.bennyrhys.consumeruser.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bennyrhys.ticket.service.TicketService;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Reference//远程引用，根据全类名进行匹配的
    TicketService ticketService;

    public void hello(){
        String ticket = ticketService.getTicket();
        System.out.println("买到票类"+ticket);
    }
}
