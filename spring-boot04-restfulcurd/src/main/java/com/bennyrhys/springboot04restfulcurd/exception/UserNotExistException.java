package com.bennyrhys.springboot04restfulcurd.exception;

public class UserNotExistException extends RuntimeException{
    /**
     * 构造器重写
     */
    public UserNotExistException() {
        super("用户不存在");

    }
}
