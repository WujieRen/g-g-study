package cn.rwj.study.flink.kafka;

/**
 * @author rwj
 * @since 2023/5/10
 */

import cn.hutool.core.util.IdUtil;
import cn.rwj.study.flink.mysql.UserSinkToMysql;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.connector.jdbc.JdbcStatementBuilder;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.h2.util.json.JSONObject;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Chris Chan
 * Create on 2021/5/22 7:23
 * Use for:
 * Explain: Flink流式处理从Kafka获取的数据
 */
public class Test implements Serializable {
    public static void main(String[] args) throws Exception {

        System.setProperty("java.security.krb5.conf", "D:/test.conf");
        System.setProperty("java.security.auth.login.config", "D:/tmp/kafka_jaas.conf");

        new Test().execute(args);
    }

    private void execute(String[] args) throws Exception {
        //获取执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);

        //配置kafka
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "10.114.12.46:6667");
        properties.put("group.id", "flink_group_1");
        //从socket获取数据
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>("test", new MySimpleStringSchema(), properties);
        consumer.setStartFromLatest();
        consumer.setCommitOffsetsOnCheckpoints(true);
        DataStream<String> streamSource = env.addSource(consumer);

        streamSource.print();

        /** -----------------------    写法一   ------------------------------------ */
        /*SingleOutputStreamOperator<String> map = streamSource.map((MapFunction<String, String>) value -> value);
        JdbcStatementBuilder<String> jdbcStatementBuilder = (preparedStatement, msg) -> {
            preparedStatement.setLong(1, IdUtil.getSnowflakeNextId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(3, msg);
        };
        JdbcConnectionOptions jdbcConnectionOptions = new JdbcConnectionOptions
                .JdbcConnectionOptionsBuilder()
                .withUrl("jdbc:mysql://192.168.66.111:3306/g_g_study")
                .withDriverName("com.mysql.cj.jdbc.Driver")
                .withUsername("root")
                .withPassword("123qwe")
                .build();
        JdbcExecutionOptions executionOptions = JdbcExecutionOptions.builder()
                .withBatchSize(1000)
                .withBatchIntervalMs(200)
                .withMaxRetries(5)
                .build();
        map.addSink(JdbcSink.sink(" insert into test_flink_write_mq (id, write_time, message)values (?,?,?)",
                jdbcStatementBuilder,
                executionOptions,
                jdbcConnectionOptions));*/
        /** 也可以简写为如下 */
        streamSource.filter(Objects::nonNull).map((MapFunction<String, String>) value -> value)
                .addSink(
                        JdbcSink.sink(
                                "insert into g_g_study.test_flink_write_mq(id, write_time, message)values(?, ?, ?);",
                                (statement, msg) -> {
                                    statement.setLong(1, IdUtil.getSnowflakeNextId());
                                    statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                                    statement.setString(3, msg);
                                },
                                JdbcExecutionOptions.builder()
                                        .withBatchSize(1000)
                                        .withBatchIntervalMs(200)
                                        .withMaxRetries(5)
                                        .build(),
                                new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                                        .withUrl("jdbc:mysql://192.168.66.111:3306/g_g_study")
                                        .withDriverName("com.mysql.cj.jdbc.Driver")
                                        .withUsername("root")
                                        .withPassword("123qwe")
                                        .build()
                        )
                );


        /** -----------------------    写法二   ------------------------------------ */
//        streamSource.map((MapFunction<String, String>) value -> value).addSink(new MsgSinkToMysql());




        env.execute();
    }
}