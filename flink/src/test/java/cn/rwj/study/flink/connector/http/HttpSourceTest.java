package cn.rwj.study.flink.connector.http;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.junit.Test;

/**
 * 需要在 sql-client.sh 测试，这里测试没测成功，因为 flink 会在启动时加载实现了 org.apache.flink.table.factories.DynamicTableSourceFactory
 * @author rwj
 * @since 2023/8/21
 */
public class HttpSourceTest {


    @Test
    public void test() {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.BATCH);
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

        String httpSource = "CREATE TABLE cust_http_source( \n" +
                            "    id string, \n" +
                            "    name string, \n" +
                            "    sex string \n" +
                            ") WITH ( \n" +
                            " 'connector' = 'http', \n" +
                            " 'http.url' = 'http://192.168.66.100:8888', \n" +
                            " 'http.interval' = '1000', \n" +
                            " 'format' = 'csv' \n" +
                            ") \n";
        tableEnv.executeSql(httpSource);

        /*String httpSink = "CREATE TABLE cust_http_sink(\n" +
                          "    id string, \n" +
                          "    name string, \n" +
                          "    sex string \n" +
                          ")WITH(\n" +
                          "    'connector' = 'print'\n" +
                          ") \n";
        tableEnv.executeSql(httpSource);*/


        String slctSql = "SELECT id, name, sex FROM cust_http_source";
        tableEnv.sqlQuery(slctSql).execute().print();

//        String instSql = "INSERT into cust_http_sink SELECT id, name, sex FROM cust_http_source;";
//        tableEnv.executeSql(instSql).print();
    }

}
