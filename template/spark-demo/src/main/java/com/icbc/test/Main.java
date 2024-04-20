package com.icbc.test;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;

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
                .config("hive.metastore.uris", "thrift://xxx:9083")
//                .config("spark.sql.warehouse.dir", "hdfs://xxx:9000/user/hive/warehouse")
//                .config("hive.metastore.uris", args[0])
//                .config("spark.sql.warehouse.dir", args[1])
                .enableHiveSupport()
                .getOrCreate();
        // 从Hive表中读取数据
//        spark.sql("show databases").show();
//        spark.sql("SELECT * FROM " + args[0]).show();
        spark.sql("SELECT * FROM b2a.user_basic").show();

        // 关闭SparkSession
        spark.stop();
    }
}
