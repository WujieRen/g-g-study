package cn.rwj.study.flink.udf.agg.WeightedAvg;

import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

import static org.apache.flink.table.api.Expressions.$;

/**
 * @author rwj
 * @since 2023/8/16
 */
public class MyAggregateFunctionDemo {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Tuple3<String, Integer, Integer>> scoreWeightDS = env.fromElements(
                Tuple3.of("zs", 80, 3),
                Tuple3.of("zs", 90, 4),
                Tuple3.of("zs", 95, 4),
                Tuple3.of("ls", 75, 4),
                Tuple3.of("ls", 65, 4),
                Tuple3.of("ls", 85, 4)
        );

        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

        Table scoreWeightTable = tableEnv.fromDataStream(scoreWeightDS, $("f0").as("name"), $("f1").as("score"), $("f2").as("weight"));
        tableEnv.createTemporaryView("scores", scoreWeightTable);

        // TODO 2.注册函数
        tableEnv.createTemporaryFunction("WeightedAvg", WeightedAvg.class);

        // TODO 3.调用 自定义函数
        tableEnv
                .sqlQuery("select name, WeightedAvg(score, weight)  from scores group by name")
                .execute()
                .print();
    }
}
