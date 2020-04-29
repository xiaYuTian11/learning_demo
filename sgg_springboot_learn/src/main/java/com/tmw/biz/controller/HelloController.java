package com.tmw.biz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TMW
 * @since 2020/3/28 19:11
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/index")
    public String index() {
        return "hello springboot";
    }

}
