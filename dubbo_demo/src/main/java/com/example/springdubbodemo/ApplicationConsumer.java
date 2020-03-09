package com.example.springdubbodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author TMW
 * @since 2020/3/9 15:40
 */
@SpringBootApplication
@ComponentScan("com.example.springdubbodemo.controller")
public class ApplicationConsumer {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConsumer.class);
    }

}
