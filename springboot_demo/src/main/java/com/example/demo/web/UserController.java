package com.example.demo.web;

import com.example.demo.properties.TmwProperties;
import com.example.demo.util.MyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotBlank;

/**
 * @author TMW
 * @since 2020/3/8 13:45
 */
@Controller
@Validated
@Slf4j
public class UserController {

    @Autowired
    private TmwProperties tmwProperties;

    @RequestMapping("/user")
    @ResponseBody
    public String index(@NotBlank(message = "name 不能为空") String name) {
        log.info("hello world");
        log.info(MyUtil.getTmw());
        return tmwProperties.toString();
    }

    @RequestMapping("/hello")
    public String index() {
        // System.out.println(1/0);
        return "hello1";
    }
}
