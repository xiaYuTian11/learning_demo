package com.demo.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author TMW
 * @since 2020/3/19 17:22
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/index/{name}")
    public String hello(@PathVariable("name") String name) {
        return "hello " + name;
    }

}
