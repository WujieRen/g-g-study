package cn.rwj.study.flink.mysql;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author rwj
 * @since 2023/6/27
 */
public class Main {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<User> userDataStreamSource = env.addSource(new UserSourceFromMysql());
        userDataStreamSource.print();

        SingleOutputStreamOperator<User> userStreamOperator = userDataStreamSource.map((MapFunction<User, User>) value -> value);

//        userStreamOperator.addSink(new UserSinkToMysql());
        userStreamOperator.addSink(
                JdbcSink.sink(
                        "insert into user(name,address,sex)values(?,?,?);",
                        (statement, user) -> {
                            statement.setString(1, user.name);
                            statement.setString(2, user.address);
                            statement.setInt(3, user.sex);
                        },
                        new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                                .withUrl("jdbc:mysql://192.168.66.111:3306/g_g_study")
                                .withDriverName("com.mysql.cj.jdbc.Driver")
                                .withUsername("root")
                                .withPassword("123qwe")
                                .build()
                )
        );

        env.execute();
    }

}
