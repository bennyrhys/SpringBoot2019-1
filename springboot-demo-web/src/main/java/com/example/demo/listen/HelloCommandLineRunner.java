package com.example.demo.listen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class HelloCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.printf("CommandLineRunner......run...."+ Arrays.asList(args ));
    }
}
