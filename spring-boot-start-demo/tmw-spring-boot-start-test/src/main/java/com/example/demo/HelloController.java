package com.example.demo;

import com.tmw.demo.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TMW
 * @since 2020/4/6 17:40
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    HelloService helloService;
    @GetMapping("/")
    public String hello(String name){
      return   helloService.helloStart(name);
    }
}
