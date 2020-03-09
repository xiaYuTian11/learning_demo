package com.example.springdubbodemo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.springdubbodemo.dubbo_service.TestServiceIF;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TMW
 * @since 2020/3/9 15:29
 */
@RestController
@RequestMapping("/api/test")
public class HelloController {

    /**
     * 不是在一个工程中需要新建一个同名空类
     */
    @Reference(timeout = 3000,retries = 0)
    private TestServiceIF testServiceIF;

    @GetMapping("/hello")
    public String hello() {
        // System.out.println(testServiceIF.testDubbo());
        return testServiceIF.testDubbo();
    }

}
