package com.demo.sb_jooq.comtroller;

import com.demo.sb_jooq.generator.tables.pojos.User;
import com.demo.sb_jooq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author TMW
 * @since 2020/1/19 17:15
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User add(@RequestBody User user) {
        userService.create(user);
        return user;
    }

    /**
     * 根据id删除用户
     *
     * @param user_id
     */
    @RequestMapping(value = "/delete/{user_id}", method = RequestMethod.GET)
    public void delete(@PathVariable("user_id") String user_id) {
        userService.delete(user_id);
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public User edit(@RequestBody User user) {
        userService.update(user);
        return user;
    }

    /**
     * 根据id获取用户
     *
     * @param user_id
     * @return
     */
    @RequestMapping(value = "/view/{user_id}", method = RequestMethod.GET)
    public User view(@PathVariable("user_id") String user_id) {
        return userService.retrieve(user_id);
    }

    /**
     * 根据条件获取用户列表
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<User> list(@RequestBody Map<String, Object> params) {
        return userService.queryForList(params);
    }

}
