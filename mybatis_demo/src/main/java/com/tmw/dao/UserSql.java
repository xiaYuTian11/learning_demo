package com.tmw.dao;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author TMW
 * @since 2020/3/23 17:18
 */
public class UserSql {
    public String getSql(int id) {
        return new SQL() {{
            SELECT("*").FROM("user").WHERE("id=#{id}");
        }}.toString();
    }

    public String findList() {
        return new SQL() {{
            SELECT("*").FROM("user");
        }}.toString();
    }

}
