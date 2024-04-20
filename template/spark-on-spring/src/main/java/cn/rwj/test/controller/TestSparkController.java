package cn.rwj.test.controller;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author rwj
 * @since 2024/4/20
 */
@RestController
@RequestMapping("spark")
public class TestSparkController {

    @Resource
    private SparkSession sparkSession;

    @GetMapping
    public List<String> test() {
        Dataset<Row> sql = sparkSession.sql("SHOW DATABASES");
//        Dataset<Row> sql = sparkSession.sql("SELECT *FROM b2a.user_basic");
        sql.show();
        return sql.javaRDD()
                .map((Function<Row, String>) row -> (String) row.get(0))
                .collect();
    }
}
