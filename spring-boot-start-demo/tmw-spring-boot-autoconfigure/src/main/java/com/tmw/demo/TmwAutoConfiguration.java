package com.tmw.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author TMW
 * @since 2020/4/6 17:33
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(TmwProperties.class)
public class TmwAutoConfiguration {

    @Autowired
    TmwProperties tmwProperties;

    @Bean
    public HelloService getHelloService() {
        HelloService helloService = new HelloService();
        helloService.setTmwProperties(tmwProperties);
        return helloService;
    }

}
