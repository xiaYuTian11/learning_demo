package com.tmw.dao;

import com.tmw.entity.User;
import org.apache.ibatis.jdbc.SQL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author TMW
 * @since 2020/3/23 16:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void findById() {
        User user = userMapper.findByIdBySql(1);
        System.out.println(user);
    }

    @Test
    public void testFindById() {
        User user = userMapper.findByIdOne("user", "id", 1);
        System.out.println(user);
    }

    @Test
    public void testFindList() {
        List<User> userList = userMapper.findList();
        System.out.println(userList);
    }

}
