package com.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author TMW
 * @since 2020/3/8 13:21
 */
@Controller
public class UserController {

    @RequestMapping("/user.do")
    public void index() {
        System.out.println("hello world");
    }

}
