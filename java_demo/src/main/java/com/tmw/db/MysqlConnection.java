package com.tmw.db;

import com.mysql.cj.jdbc.Driver;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author TMW
 * @since 2020/3/22 16:08
 */
public class MysqlConnection {
    public static void main(String[] args) throws Exception {
        // String url = "jdbc:mysql://127.0.0.1:3306/springboot?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT";
        // String driverName = "com.mysql.cj.jdbc.Driver";
        // String userName = "root";
        // String password = "123456";
        InputStream resourceAsStream = MysqlConnection.class.getClassLoader().getResourceAsStream("./jdbc.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        Class.forName(properties.getProperty("driverName"));
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("userName"), properties.getProperty("password"));
        Statement statement = connection.createStatement();
        String sql = "select * from user";
        // 会sql注入
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println(resultSet);
            System.out.println(resultSet.getString(1));
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
