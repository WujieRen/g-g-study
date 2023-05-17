package cn.rwj.study.flink.kafka;

/**
 * @author rwj
 * @since 2023/5/10
 */

import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Objects;
import java.util.Properties;

/**
 * @author Chris Chan
 * Create on 2021/5/22 7:23
 * Use for:
 * Explain: Flink流式处理从Kafka获取的数据
 */
public class Test {
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
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>("realtime_ip_index_sink", new MySimpleStringSchema(), properties);
        consumer.setStartFromLatest();
        consumer.setCommitOffsetsOnCheckpoints(true);
        DataStreamSource<String> streamSource = env.addSource(consumer);
        try {
            streamSource.filter(Objects::nonNull).print().setParallelism(1);
        } catch (Exception e) {
            System.out.println("null~~~~~~~~~");
        }

        env.execute();
    }
}