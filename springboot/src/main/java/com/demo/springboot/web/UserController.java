package com.demo.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author TMW
 * @since 2020/3/8 13:45
 */
@Controller
@ResponseBody
public class UserController {
    @RequestMapping("/user")
    public void index() {
        System.out.println("hello world");
    }
}
