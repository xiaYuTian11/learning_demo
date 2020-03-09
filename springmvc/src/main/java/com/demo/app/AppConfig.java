package com.demo.app;

import com.demo.Application;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author TMW
 * @since 2020/3/8 11:46
 */
@Configuration
@ComponentScan({"com.demo"})
public class AppConfig {
    @Bean
    public Application getApplication() {
        return new Application();
    }
}
