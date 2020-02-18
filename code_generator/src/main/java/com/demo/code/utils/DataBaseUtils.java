package com.demo.code.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import com.demo.code.entity.db.Column;
import com.demo.code.entity.db.DataBase;
import com.demo.code.entity.db.Table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

/**
 * @author TMW
 * @since 2020/2/13 18:01
 */
public class DataBaseUtils {

    /**
     * 获取连接
     *
     * @param db
     * @return
     * @throws Exception
     */
    public static Connection getConnection(DataBase db) throws Exception {
        Properties p = new Properties();
        // 获取数据库备注信息
        p.put("remarksReporting", "true");
        p.put("user", db.getUserName());
        p.put("password", db.getPassWord());
        Class.forName(db.getDriver());
        return DriverManager.getConnection(db.getUrl(), p);
    }

    /**
     * 获取约束
     *
     * @param db
     * @return
     */
    public static List<String> getSchemas(DataBase db) throws Exception {
        Connection connection = getConnection(db);
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getSchemas();
        List<String> list = new ArrayList<>(resultSet.getFetchSize());
        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    /**
     * 获取数据库中的表和字段构造实体类
     * Table对象
     * <p>
     * 1.参数
     * DataBase 数据库对象
     * 2.操作步骤
     * 1.获取连接
     * 2.获取databasemetaData
     * 3.获取当前数据库中的所有表
     * 4.获取每个表中的所有字段
     * 5.封装到java对象中即可
     */
    public static List<Table> getDbInfo(DataBase db) throws Exception {
        //1.获取连接
        Connection connection = getConnection(db);
        //2.获取元数据
        DatabaseMetaData metaData = connection.getMetaData();
        //3.获取当前数据库中的所有表
        ResultSet tables = metaData.getTables(db.getDataBaseName(), null, null, new String[]{"TABLE"});

        List<Table> list = new ArrayList<>();

        while (tables.next()) {
            Table tab = new Table();
            // 表名
            String tableName = tables.getString("TABLE_NAME");
            // 类名
            String className = removePrefix(tableName);
            // 描述
            String remarks = tables.getString("REMARKS");
            // 主键
            ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
            StringBuilder keys = new StringBuilder();
            while (primaryKeys.next()) {
                String keyName = primaryKeys.getString("COLUMN_NAME");
                keys.append(keyName).append(",");
            }
            tab.setName(tableName);
            tab.setName2(className);
            tab.setComment(remarks);
            tab.setKey(keys.toString());
            //处理表中的所有字段

            ResultSet columns = metaData.getColumns(null, null, tableName, null);

            List<Column> columnList = new ArrayList<>();

            while (columns.next()) {
                Column cn = new Column();
                //构造Column对象
                //列名称
                String columnName = columns.getString("COLUMN_NAME");
                cn.setColumnName(columnName);
                //属性名
                String attName = StringUtils.toJavaVariableName(columnName);
                cn.setColumnName2(attName);
                //java类型和数据库类型
                String dbType = columns.getString("TYPE_NAME");
                cn.setColumnDbType(dbType);
                String javaType = PropertiesUtils.customMap.get(dbType);
                cn.setColumnType(javaType);
                //备注
                String columnRemark = columns.getString("REMARKS");
                cn.setColumnComment(columnRemark);
                //是否主键
                String pri = null;
                if (StringUtils.contains(columnName, keys.toString().split(","))) {
                    pri = "PRI";
                }
                cn.setColumnKey(pri);
                columnList.add(cn);
            }
            columns.close();
            tab.setColumns(columnList);
            list.add(tab);
        }
        tables.close();
        connection.close();
        return list;
    }

    /**
     * 移除前缀
     */
    public static String removePrefix(String tableName) {
        String prefix = PropertiesUtils.customMap.get("tableRemovePrefixes");
        String temp = tableName;
        if (ArrayUtil.isNotEmpty(prefix.split(","))) {
            for (String pf : prefix.split(",")) {
                temp = StringUtils.removePrefix(temp, pf, true);
            }
        }
        return StringUtils.makeAllWordFirstLetterUpperCase(temp);
    }
}
