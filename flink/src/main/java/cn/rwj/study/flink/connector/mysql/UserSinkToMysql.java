package cn.rwj.study.flink.connector.mysql;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author rwj
 * @since 2023/6/27
 */
public class UserSinkToMysql extends RichSinkFunction<User> {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://192.168.66.111:3306/g_g_study";
        String username = "root";
        String password = "123qwe";
        Class.forName( driver );
        connection = DriverManager.getConnection( url, username, password );
        String sql = "insert into user(name,address,sex)values(?,?,?);";
        preparedStatement = connection.prepareStatement( sql );
    }

    @Override
    public void close() throws Exception {
        super.close();

        if (connection != null) {
            connection.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }

    @Override
    public void invoke(User value, Context context) {
        try {
            preparedStatement.setString( 1, value.getName() );
            preparedStatement.setString( 2, value.getAddress() );
            preparedStatement.setInt( 3, value.getSex() );
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
