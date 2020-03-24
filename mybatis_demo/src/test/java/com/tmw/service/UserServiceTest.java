package com.tmw.service;

import com.tmw.dao.UserMapper;
import com.tmw.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author TMW
 * @since 2020/3/23 12:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void findById() {
        User byId = userMapper.findById(1);
        System.out.println(byId);
    }
}
