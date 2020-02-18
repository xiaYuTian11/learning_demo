package com.demo.code;

import com.demo.code.entity.db.DataBase;
import com.demo.code.entity.db.Table;
import com.demo.code.utils.DataBaseUtils;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

/**
 * @author TMW
 * @since 2020/2/17 15:15
 */
public class Demo01 {

    @Test
    public void test() throws Exception {
        DataBase db = new DataBase("mysql", "127.0.0.1", "3306", "xc_user");
        List<Table> dbInfo = DataBaseUtils.getDbInfo(db);
        for (Table s : dbInfo) {
            System.out.println(s);
        }
    }

    @Test
    public void test01() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://127.0.0.1:3306/xc_user?useUnicode=true&amp&characterEncoding=UTF8&allowMultiQueries=true&serverTimezone=GMT%2B8&useSSL=false";
        String userName = "root";
        String password = "123456";
        String tableNamePattern = "ccp%";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, userName, password);
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables("xc_user", null, null, new String[]{"TABLE"});
        while (tables.next()) {
            System.out.println(tables.getString("TABLE_NAME"));
            ResultSet columns = metaData.getColumns(null, null, tables.getString("TABLE_NAME"), null);
            while (columns.next()) {
                System.out.println(columns.getString("COLUMN_NAME"));
            }
        }
        System.out.println(metaData);
    }
}
