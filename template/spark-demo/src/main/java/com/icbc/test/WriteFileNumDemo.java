package com.icbc.test;

import com.icbc.test.entity.User;
import lombok.Builder;
import lombok.Data;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.lit;

import java.util.Arrays;
import java.util.List;

/**
 * @author rwj
 * @since 2024/7/3
 */
public class WriteFileNumDemo {

    public static void main(String[] args) {
        List<User> userList = Arrays.asList(
                User.builder().name("rwj").age(29).sex("男").build(),
                User.builder().name("sx").age(29).sex("男").build(),
                User.builder().name("wlb").age(29).sex("男").build(),
                User.builder().name("lsj").age(29).sex("男").build(),
                User.builder().name("wy").age(29).sex("女").build()
        );

        SparkSession spark = SparkSession.builder()
                .appName("test")
                .master("local")
                .config("hive.metastore.uris", "thrift://10.64.15.150:9083")
//                .config("spark.sql.adaptive.enabled", "true")  //该参数可优化spark写入hive时文件个数；但是要求spark版本>=2.4.0（具体版本记不清了）
                .enableHiveSupport()
                .getOrCreate();
        spark.conf().set("spark.sql.adaptive.enabled", "true");  //该参数可优化spark写入hive时文件个数；但是要求spark版本>=2.4.0（具体版本记不清了）
        spark.sqlContext().setConf("hive.exec.dynamic.partition", "true");
        spark.sqlContext().setConf("hive.exec.dynamic.partition.mode", "nonstrict");

        Dataset<Row> ds = spark.createDataFrame(userList, User.class);

        ds
                .withColumn("dt", lit("2024-07-04"))
                .write()
                .option("partitionOverwriteMode", "dynamic")
                .mode("overwrite")
                .insertInto("b2a.p_user");

    }



}

