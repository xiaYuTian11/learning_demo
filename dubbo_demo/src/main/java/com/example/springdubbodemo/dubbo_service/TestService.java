package com.example.springdubbodemo.dubbo_service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author TMW
 * @since 2020/3/9 15:29
 */
@Service(timeout = 3000)
@Component
public class TestService implements TestServiceIF {
    @Override
    public String testDubbo() {
        System.out.println("hello");
        return "this is provider return data!";
    }
}
