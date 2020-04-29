package com.tmw.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.tmw.dao.UserDaoImpl;
import com.tmw.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author TMW
 * @since 2020/3/24 21:58
 */
@Service
public class UserService {
    @Autowired
    private UserDaoImpl userDao;
    // @Autowired
    // private UserDao userDao;

    public User findById(int id) {
        Record record = Db.findById("user", id);
        System.out.println(record);
        User user = userDao.findById(id);
        System.out.println(user);
        return user;
    }

    // public User findById(int id) {
    //     Optional<UserBoot> byId = userDao.findById(id);
    //     System.out.println(byId.get());
    //     return null;
    // }

}
