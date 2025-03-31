package cmn.rwj.study.spark.test.resDataFileNum;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

/**
 * 开启 AQE，但不指定分区数
 *
 * @author rwj
 * @since 2025/2/6
 */
public class MainWithAQENoRepartition {

    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("WithAQENoRepartition")
                .master("local[*]") // Run locally with all available cores
                .config("spark.sql.adaptive.enabled", "true")
                .config("spark.sql.adaptive.coalescePartitions.enabled", "false") // Disable automatic merging for clarity
                .getOrCreate();

        // Create sample data
        Dataset<Long> df = createSampleData(spark);

        // Convert Long type to Row type for compatibility
        Dataset<Row> rowDF = df.toDF();

        // Write to local directory without any specific partition settings
        String outputPath = "./output/aqe_no_partition";
        rowDF.write().mode(SaveMode.Overwrite).csv(outputPath);

        System.out.println("Written output to: " + outputPath);
        spark.stop();
    }

    private static Dataset<Long> createSampleData(SparkSession spark) {
        return spark.range(0L, 1000000L); // Correctly specify start and end values as Longs
    }

}
