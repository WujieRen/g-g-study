package cn.rwj.study.flink.mysql;

import org.apache.flink.api.common.functions.MapFunction;
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

        DataStreamSource<User> userDataStreamSource = env.addSource( new UserSourceFromMysql() );
        userDataStreamSource.print();

        SingleOutputStreamOperator<User> userStreamOperator = userDataStreamSource.map((MapFunction<User, User>) value -> value);

        userStreamOperator.addSink(new UserSinkToMysql());

        env.execute();
    }

}
