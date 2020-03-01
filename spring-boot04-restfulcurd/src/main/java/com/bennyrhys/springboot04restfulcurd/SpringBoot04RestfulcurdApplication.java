package com.bennyrhys.springboot04restfulcurd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

@SpringBootApplication
public class SpringBoot04RestfulcurdApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot04RestfulcurdApplication.class, args);
    }

    @Bean
    public ViewResolver myVViewResolver(){
        return new resolveViewName();
    }

    private static class resolveViewName implements ViewResolver{

        @Override
        public View resolveViewName(String s, Locale locale) throws Exception {
            return null;
        }
    }

}
