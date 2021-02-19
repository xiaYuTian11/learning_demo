package top.tanmw.mybatis.test;

import java.sql.*;

/**
 * @author TMW
 * @date 2021/2/18 10:19
 */
public class JDBCTest {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接
            String url = "jdbc:mysql://47.110.133.228:3306/demo";
            String user = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, user, password);
            String sql = "select * from  jobs where job_id = ?;";
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1, "AC_ACCOUNT");
            resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("job_id"));
                System.out.println(resultSet.getString("job_title"));
                System.out.println(resultSet.getString("min_salary"));
                System.out.println(resultSet.getString("max_salary"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet !=null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(prepareStatement!=null){
                try {
                    prepareStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
