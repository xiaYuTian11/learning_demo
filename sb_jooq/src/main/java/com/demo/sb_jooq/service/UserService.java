package com.demo.sb_jooq.service;

import com.demo.sb_jooq.generator.tables.pojos.User;

import java.util.List;
import java.util.Map;

/**
 * @author TMW
 * @since 2020/1/19 17:17
 */
public interface UserService {
    /**
     * 创建用户
     *
     * @param user
     */
    void create(User user);

    /**
     * 根据id删除用户
     *
     * @param user_id
     */
    void delete(String user_id);

    /**
     * 更新用户
     *
     * @param user
     */
    void update(User user);

    /**
     * 根据id获取用户
     *
     * @param user_id
     * @return
     */
    User retrieve(String user_id);

    /**
     * 根据条件获取用户列表
     *
     * @param params
     * @return
     */
    List<User> queryForList(Map<String, Object> params);
}
