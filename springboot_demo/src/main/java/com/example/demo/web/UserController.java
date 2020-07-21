package com.example.demo.web;

import com.example.demo.properties.TmwProperties;
import com.example.demo.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author TMW
 * @since 2020/3/8 13:45
 */
@RestController
@Validated
public class UserController {

    @Autowired
    private TmwProperties tmwProperties;

    @RequestMapping("/user")
    public String index(@NotBlank(message = "name 不能为空") String name) {
        System.out.println("hello world");
        System.out.println(MyUtil.getTmw());
        return tmwProperties.toString();
    }
}
