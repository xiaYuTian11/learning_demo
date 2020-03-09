package com.example.springdubbodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author TMW
 * @since 2020/3/9 15:38
 */
@SpringBootApplication
@ComponentScan("com.example.springdubbodemo.dubbo_service")
public class ApplicationProvider {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationProvider.class);
    }

}
