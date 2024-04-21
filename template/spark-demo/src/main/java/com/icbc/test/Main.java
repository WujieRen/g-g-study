package com.icbc.test;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf()
                .setAppName("Spark example")
                .setMaster("local[*]");
        SparkSession spark = SparkSession.builder()
                .appName("demo")
                .config(sparkConf)
                .config("hive.metastore.uris", "thrift://10.64.15.150:9083")
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
        toWroteDS.write().saveAsTable("b2a.tmp_user");
//        spark.sql("select * from tmp_basic_user").show();
        // 关闭SparkSession
        spark.stop();
    }
}
