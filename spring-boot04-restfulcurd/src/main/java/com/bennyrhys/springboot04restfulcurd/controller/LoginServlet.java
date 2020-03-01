package com.bennyrhys.springboot04restfulcurd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginServlet {

    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map,
                        HttpSession session
                        ){
        if (!StringUtils.isEmpty(username) && "123456".equals(password)){
            //登陆成功，进入管理页面
//            return "dashboard";
//            防止表单因转发，表单重复提交

            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else {
            //登陆失败，回到登陆页面
            map.put("msg","用户名，密码错误！");

            return "login";
        }
    }
}
