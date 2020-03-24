package com.tmw.controller;

import com.tmw.entity.User;
import com.tmw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TMW
 * @since 2020/3/23 12:26
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("findById")
    public User findById(@RequestParam("id") int id) {
        return userService.findById(id);
    }

}
