package com.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author TMW
 * @since 2020/3/8 12:01
 */
@Controller
public class UserController {
    @RequestMapping(value = "/user.do")
    public void login() {
        System.out.println("hello world");
    }
}
