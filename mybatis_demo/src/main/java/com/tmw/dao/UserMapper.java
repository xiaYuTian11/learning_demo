package com.tmw.dao;

import com.tmw.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author TMW
 * @since 2020/3/23 12:22
 */
@Mapper
public interface UserMapper {
    // @Select("select * from user where id = #{id}")
    User findById(@Param("id") int id);

    @Select("select * from ${tableName} where ${column}= #{id}")
    User findByIdOne(@Param("tableName") String tableName, @Param("column") String column, @Param("id") int id);

    @SelectProvider(value = com.tmw.dao.UserSql.class, method = "getSql")
    User findByIdBySql(@Param("id") int id);

    @SelectProvider(value = com.tmw.dao.UserSql.class, method = "findList")
    List<User> findList();
}
