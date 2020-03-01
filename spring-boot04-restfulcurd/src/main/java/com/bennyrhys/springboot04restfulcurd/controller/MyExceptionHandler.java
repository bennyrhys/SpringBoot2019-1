package com.bennyrhys.springboot04restfulcurd.controller;

import com.bennyrhys.springboot04restfulcurd.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice//要成为异常处理器必须
public class MyExceptionHandler {
    /*//1处理一个异常
    @ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    public Map<String,Object>  handelException(Exception e){
        Map<String,Object> map = new HashMap<>();
        map.put("code","user.notexist");
        map.put("message",e.getMessage());
        return map;

    }*/
//2处理一个异常
   // @ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    public String  handelException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();

//        //传入自己的状态码
//        Integer statusCode = (Integer) request
//                .getAttribute("javax.servlet.error.status_code");
        request.setAttribute("javax.servlet.error.status_code",500);

        map.put("code","user.notexist");
        map.put("message",e.getMessage());


        request.setAttribute("ext",map);
        return "forward:/error";

    }
}
