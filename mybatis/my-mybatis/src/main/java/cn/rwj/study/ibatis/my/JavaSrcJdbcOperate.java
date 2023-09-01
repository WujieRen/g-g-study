package cn.rwj.study.ibatis.my;

import cn.rwj.study.ibatis.my.model.User;

import java.sql.*;

/**
 * Hello world!
 */
public class JavaSrcJdbcOperate {


    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // 1、加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2、通过驱动管理类获取数据库链接
            con = DriverManager.getConnection("jdbc:mysql://192.168.66.111:3306/mybatis", "root", "123qwe");
            // 定义sql语句？表示占位符
            String sql = "select * from my_user where name = ?";
            // 3、获取预处理statement
            statement = con.prepareStatement(sql);
            // 设置参数，第一个参数为sql语句中参数的序号(从1开始)，第二个参数为设置的参数值
            statement.setString(1, "rwj");
            // 4、执行sql，获取出结果集
            resultSet = statement.executeQuery();
            // 遍历查询结果集
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("name");
                // 封装User
                User user = new User();
                user.setId(id);
                user.setName(username);
                System.out.println(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
