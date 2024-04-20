package cn.rwj.test.config;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author rwj
 * @since 2024/4/20
 */
@Configuration
public class SparkConfig {

    @Bean(destroyMethod = "stop")
    public SparkSession sparkSession() {
        return SparkSession.builder()
                .appName("demo")
                .config("hive.metastore.uris", "thrift://xxx:9083")
                .master("local[*]")
                .enableHiveSupport()
                .getOrCreate();
    }

    //TODO: 注意 ConditionalOnClass / ConditionalOnBean 可能无法生效的问题，
    // 参考：https://blog.csdn.net/forezp/article/details/84313907
    @ConditionalOnClass(SparkConfig.class)
    public JavaSparkContext sparkContext() {
        return JavaSparkContext.fromSparkContext(this.sparkSession().sparkContext());
    }


}
