package cn.rwj.study.flink.udf.agg.WeightedAvg;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.table.functions.AggregateFunction;

/**
 * @author rwj
 * @since 2023/8/16
 */
public class WeightedAvg extends AggregateFunction<Double, Tuple2<Integer, Integer>> {


    @Override
    public Double getValue(Tuple2<Integer, Integer> integerIntegerTuple2) {
        return integerIntegerTuple2.f0 * 1D / integerIntegerTuple2.f1;
    }

    @Override
    public Tuple2<Integer, Integer> createAccumulator() {
        return Tuple2.of(0, 0);
    }

    public void accumulate(Tuple2<Integer, Integer> acc,Integer score,Integer weight){
        acc.f0 += score * weight;  // 加权总和 =  分数1 * 权重1 + 分数2 * 权重2 +....
        acc.f1 += weight;         // 权重和 = 权重1 + 权重2 +....
    }

}
