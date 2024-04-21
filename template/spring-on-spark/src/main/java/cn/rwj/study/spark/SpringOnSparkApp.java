package cn.rwj.study.spark;

import cn.rwj.study.spark.utils.SpringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.List;

/**
 * Reference：
 *  - https://blog.csdn.net/poxiaomeng187/article/details/123050445
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringOnSparkApp implements CommandLineRunner {
    public static void main(String[] args) {
        /*if (args == null || args.length == 0) {
            System.out.println("缺少资源id,程序退出");
            return;
        }*/
        SpringApplication.run(SpringOnSparkApp.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("================== 程序开始 ====================");

        SparkConf sparkConf = new SparkConf()
                .setAppName("Spark example")
                .setMaster("local[*]");
        SparkSession spark = SparkSession.builder()
                .appName("demo")
                .config(sparkConf)
                .config("hive.metastore.uris", "thrift://xxx:9083")
//                .config("spark.sql.warehouse.dir", "hdfs://xxx:9000/user/hive/warehouse")
//                .config("hive.metastore.uris", args[0])
//                .config("spark.sql.warehouse.dir", args[1])
                .enableHiveSupport()
                .getOrCreate();
        // 从Hive表中读取数据
//        spark.sql("show databases").show();
//        spark.sql("SELECT * FROM " + args[0]).show();
        Dataset<Row> srcData = spark.sql("SELECT * FROM b2a.user_basic");
        srcData.show();

        final Tuple2<String, String>[] dtypes = srcData.dtypes();
        JavaRDD<Row> toWriteData = srcData.javaRDD().map((Function<Row, Row>) row -> RowFactory.create(row.get(0), row.get(1), row.get(2), String.valueOf(row.get(3))));

        List<StructField> structFields = new ArrayList<>();
        for (int i = 0; i < dtypes.length; i++) {
            structFields.add(DataTypes.createStructField(dtypes[i]._1, DataTypes.StringType, true));
        }

        Dataset<Row> toWroteDS = spark.createDataFrame(toWriteData, DataTypes.createStructType(structFields));
//        toWroteDS.registerTempTable("tmp_basic_user");
        toWroteDS.write().saveAsTable("b2a.tmp_user_2");
//        spark.sql("select * from tmp_basic_user").show();
        // 关闭SparkSession
        spark.stop();

        SpringApplication.exit(SpringUtils.getApplicationContext());
        System.out.println("================== 程序结束 ====================");
    }
}
