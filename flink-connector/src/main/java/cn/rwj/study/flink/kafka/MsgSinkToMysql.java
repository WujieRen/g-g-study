package cn.rwj.study.flink.kafka;

import cn.hutool.core.util.IdUtil;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author rwj
 * @since 2023/6/27
 */
public class MsgSinkToMysql extends RichSinkFunction<String> {

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
        String sql = "insert into g_g_study.test_flink_write_mq(id, write_time, message)values(?, ?, ?);";
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
    public void invoke(String value, Context context) {
        try {
            preparedStatement.setLong( 1,  IdUtil.getSnowflakeNextId());
            preparedStatement.setTimestamp( 2, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString( 3, value);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
