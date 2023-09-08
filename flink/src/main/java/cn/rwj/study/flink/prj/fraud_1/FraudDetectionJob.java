package cn.rwj.study.flink.prj.fraud_1;

import cn.rwj.study.flink.prj.fraud_1.entity.Alert;
import cn.rwj.study.flink.prj.fraud_1.entity.Transaction;
import cn.rwj.study.flink.prj.fraud_1.func.FraudDetector;
import cn.rwj.study.flink.prj.fraud_1.sink.AlertSink;
import cn.rwj.study.flink.prj.fraud_1.source.TransactionSource;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author rwj
 * @since 2023/9/8
 */
public class FraudDetectionJob {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Transaction> transactions = env
                .addSource(new TransactionSource())
                .name("transactions");

        DataStream<Alert> alerts = transactions
                .keyBy(Transaction::getAccountId)
                .process(new FraudDetector())
                .name("fraud-detector");

        alerts
                .addSink(new AlertSink())
                .name("send-alerts");

        env.execute("Fraud Detection");
    }

}
