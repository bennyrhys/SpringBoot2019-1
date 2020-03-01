package com.bennyrhys.mybits;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(value = "com.bennyrhys.mybits.mapper")
@SpringBootApplication
public class MybitsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybitsApplication.class, args);
    }

}
