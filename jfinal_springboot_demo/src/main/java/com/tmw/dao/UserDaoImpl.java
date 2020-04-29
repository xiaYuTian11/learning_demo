package com.tmw.dao;

import com.jfinal.aop.Aop;
import com.jfinal.plugin.activerecord.Model;
import com.tmw.entity.User;
import com.tmw.entity.UserBoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author TMW
 * @since 2020/3/24 21:58
 */
@Repository
public class UserDaoImpl {

    public User userDb = Aop.get(User.class);
    // @Autowired
    // public UserDao userDao;

    public User findById(int id) {
        Model model = userDb.findById(id);
        System.out.println(model);
        User user = userDb.findById(id);
        return user;
    }

    // public User findById(int id) {
    //     UserBoot userBoot = userDao.findById(id).get();
    //     System.out.println(userBoot);
    //     return null;
    // }

}
