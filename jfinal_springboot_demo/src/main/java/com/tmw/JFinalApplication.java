package com.tmw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author TMW
 * @since 2020/3/24 21:38
 */
@SpringBootApplication
// @EntityScan("com.tmw.entity")
// @ComponentScan("com.tmw")
public class JFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JFinalApplication.class, args);
    }
}
