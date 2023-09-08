package cn.rwj.study.flink.prj.fraud_1.func;

import cn.rwj.study.flink.prj.fraud_1.entity.Alert;
import cn.rwj.study.flink.prj.fraud_1.entity.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

/**
 * @author rwj
 * @since 2023/9/8
 */
@Slf4j
public class FraudDetector extends KeyedProcessFunction<Long, Transaction, Alert> {

    private static final long serialVersionUID = 1L;

    @Override
    public void processElement(Transaction transaction,
                               KeyedProcessFunction<Long, Transaction, Alert>.Context context,
                               Collector<Alert> collector) throws Exception {
        log.info("数据：" + transaction.toString());

        Alert alert = new Alert();
        alert.setId(transaction.getAccountId());

        collector.collect(alert);
    }

}
