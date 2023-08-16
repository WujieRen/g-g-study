package cn.rwj.study.flink.udf.tableagg;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

import static org.apache.flink.table.api.Expressions.$;
import static org.apache.flink.table.api.Expressions.call;

/**
 * @author rwj
 * @since 2023/8/16
 */
public class MyTableAggregateFunctionDemo {

    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //  姓名，分数，权重
        DataStreamSource<Integer> numDS = env.fromElements(3, 6, 12, 5, 8, 9, 4);

        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

        Table numTable = tableEnv.fromDataStream(numDS, $("num"));

        // TODO 2.注册函数
        tableEnv.createTemporaryFunction("Top2", Top2.class);

        // TODO 3.调用 自定义函数: 只能用 Table API
        numTable
                .flatAggregate(call("Top2", $("num")).as("value", "rank"))
                .select($("value"), $("rank"))
                .execute()
                .print();
    }

}
