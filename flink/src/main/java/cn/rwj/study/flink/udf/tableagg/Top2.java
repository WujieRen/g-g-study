package cn.rwj.study.flink.udf.tableagg;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.table.functions.TableAggregateFunction;
import org.apache.flink.util.Collector;

/**
 * @author rwj
 * @since 2023/8/16
 */
public class Top2 extends TableAggregateFunction<Tuple2<Integer, Integer>, Tuple2<Integer, Integer>> {

    @Override
    public Tuple2<Integer, Integer> createAccumulator() {
        return Tuple2.of(0, 0);
    }

    /**
     * 每来一个数据调用一次，比较大小，更新 最大的前两个数到 acc中
     *
     * @param acc 累加器
     * @param num 过来的数据
     */
    public void accumulate(Tuple2<Integer, Integer> acc, Integer num) {
        if (num > acc.f0) {
            // 新来的变第一，原来的第一变第二
            acc.f1 = acc.f0;
            acc.f0 = num;
        } else if (num > acc.f1) {
            // 新来的变第二，原来的第二不要了
            acc.f1 = num;
        }
    }


    /**
     * 输出结果： （数值，排名）两条最大的
     *
     * @param acc 累加器
     * @param out 采集器<返回类型>
     */
    public void emitValue(Tuple2<Integer, Integer> acc, Collector<Tuple2<Integer, Integer>> out) {
        if (acc.f0 != 0) {
            out.collect(Tuple2.of(acc.f0, 1));
        }
        if (acc.f1 != 0) {
            out.collect(Tuple2.of(acc.f1, 2));
        }
    }

}
