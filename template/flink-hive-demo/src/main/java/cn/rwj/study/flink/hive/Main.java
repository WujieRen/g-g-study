package cn.rwj.study.flink.hive;

import cn.hutool.json.JSONUtil;
import cn.rwj.study.flink.hive.function.Batch2ApiFunc;
import cn.rwj.study.flink.hive.model.Params;
import cn.rwj.study.flink.hive.utils.MetaTypeInfoUtil;
import cn.rwj.study.flink.hive.utils.ParamUtil;
import cn.rwj.study.flink.hive.utils.SqlUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.catalog.Catalog;
import org.apache.flink.table.catalog.hive.HiveCatalog;
import org.apache.flink.types.Row;


/**
 * Hello world!
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        Params params = ParamUtil.parseParams(args);
        System.out.println("=============参数信息==================");
        System.out.println(JSONUtil.toJsonStr(params));
        System.out.println("=============参数信息==================");

        // 初始化环境
        // 解析初始化参数，并初始化环境
        StreamExecutionEnvironment env = initEnv("初始化环境信息Json");
        StreamTableEnvironment tableEnv = useHive(env);

        /**
         * Source：
         *      解析元信息 & 从数据源表获取数据
         */
        String sourceSql = SqlUtil.getSourceSql(params.getSourceInfo());
        Table wide = tableEnv.sqlQuery(sourceSql);


        /**
         * Transform：
         *      批转联逻辑，每消费一条数据据，就要调一次 http 接口
         */
        SingleOutputStreamOperator<Row> map = tableEnv.toDataStream(wide)
                .flatMap(new Batch2ApiFunc(params))
                .name("调api")
                .returns(MetaTypeInfoUtil.getTypeInformation(params));

        /**
         * Sink:
         *      创建数据汇表 & 写入
         */
        // 建表
        String crtSinkTableSql = SqlUtil.getCrtSinkTableSql(params);
        tableEnv.executeSql(crtSinkTableSql);
        // 写入结果
        tableEnv.createTemporaryView("res", map);
        tableEnv.executeSql(SqlUtil.getInsertSinkSql(params.getSinkInfo()));

        // 查询结果
        tableEnv.executeSql(String.format("SELECT * FROM %s", params.getSinkInfo().getTableName())).print();
    }

    private static StreamExecutionEnvironment initEnv(String initEnvArgs) {
        Configuration conf = new Configuration();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setRuntimeMode(RuntimeExecutionMode.BATCH);
        return env;
    }

    private static StreamTableEnvironment useHive(StreamExecutionEnvironment env) {
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

        String myHive = "myHive";
        Catalog hiveCatalog = new HiveCatalog(myHive, "icbc", "D:\\tmp\\www\\hive-conf");
        tableEnv.registerCatalog(myHive, hiveCatalog);
        tableEnv.useCatalog(myHive);
        tableEnv.useDatabase("icbc");

        return tableEnv;
    }

}
