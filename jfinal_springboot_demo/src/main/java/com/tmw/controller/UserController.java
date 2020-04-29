package com.tmw.controller;

import com.jfinal.core.Controller;
import com.tmw.entity.User;
import com.tmw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TMW
 * @since 2020/3/24 21:52
 */
@RestController
@RequestMapping("/user")
public class UserController extends Controller {
    @Autowired
    private UserService userService;

    @RequestMapping("/findById")
    public User findById(@RequestParam("id") int id) {
        return userService.findById(id);
    }

}
