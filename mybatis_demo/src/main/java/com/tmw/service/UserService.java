package com.tmw.service;

import com.tmw.dao.UserMapper;
import com.tmw.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author TMW
 * @since 2020/3/23 12:24
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findById(int id) {
        return userMapper.findById(id);
    }
}
