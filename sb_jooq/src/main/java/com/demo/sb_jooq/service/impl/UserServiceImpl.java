package com.demo.sb_jooq.service.impl;

import com.demo.sb_jooq.generator.tables.pojos.User;
import com.demo.sb_jooq.service.UserService;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.demo.sb_jooq.generator.Tables.USER;

/**
 * @author TMW
 * @since 2020/1/19 17:18
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private DSLContext dsl;

    @Override
    public void create(User user) {
        int execute = dsl.insertInto(USER, USER.USER_ID, USER.NAME, USER.INTRO)
                .values(user.getUserId(), user.getName(), user.getIntro()).execute();
        System.out.println(execute);
    }

    @Override
    public void delete(String user_id) {
        int execute = dsl.deleteFrom(USER).where(USER.USER_ID.eq(user_id)).execute();
        System.out.println(execute);
    }

    @Override
    public void update(User user) {
        int execute = dsl.update(USER).set(USER.NAME, user.getName()).where(USER.USER_ID.eq(user.getUserId())).execute();
        System.out.println(execute);
    }

    @Override
    public User retrieve(String user_id) {
        // TODO: 2020/1/19 会出现空指针异常
        User user = dsl.select().from(USER).where(USER.USER_ID.eq(user_id)).fetchOne().into(User.class);
        return user;
    }

    @Override
    public List<User> queryForList(Map<String, Object> params) {
        dsl.select().from(USER).limit(1000, 10);
        return null;
    }
}
