package cn.rwj.study.flink.connector.mysql;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import java.sql.*;

/**
 * @author rwj
 * @since 2023/6/27
 */
public class UserSourceFromMysql extends RichSourceFunction<User> {

    PreparedStatement preparedStatement;
    private Connection connection;

    public UserSourceFromMysql() {
        super();
    }


    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://192.168.66.111:3306/g_g_study";
        String username = "root";
        String password = "123qwe";
        Class.forName(driver);
        connection = DriverManager.getConnection( url, username, password );
        String sql = "select * from user;";
        preparedStatement = connection.prepareStatement( sql );
    }

    @Override
    public void run(SourceContext<User> sourceContext) throws Exception {
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User( resultSet.getInt( "id" ),
                        resultSet.getString( "name" ).trim(),
                        resultSet.getString( "address" ).trim(),
                        resultSet.getInt( "sex" ) );
                sourceContext.collect( user );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancel() {
        try {
            closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        super.close();
        closeConnection();
    }

    private void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}
