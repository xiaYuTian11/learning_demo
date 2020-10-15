package com.example.uidgenerator.controller;

import com.baidu.fsg.uid.UidGenerator;
import com.example.uidgenerator.service.UidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TMW
 * @date 2020/10/15 11:27
 */
@RestController
@RequestMapping("/")
public class UidController {
    @Autowired
    private UidService service;

    @Autowired
    private UidGenerator uidGenerator;
    @GetMapping("/nextId")
    public void nextId() {
        System.out.println(uidGenerator.getUID());
    }
}
