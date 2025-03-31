package cmn.rwj.study.spark.test.resDataFileNum;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

/**
 * 开启 AQE，并指定分区数
 *
 * @author rwj
 * @since 2025/2/6
 */
public class MainWithAEWithRepartition {

    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("WithAEWithRepartition")
                .master("local[*]") // Run locally with all available cores
                .config("spark.sql.adaptive.enabled", "true")
                .config("spark.sql.adaptive.coalescePartitions.enabled", "true")
                .config("spark.sql.adaptive.advisoryPartitionSizeInBytes", "67108864") // Default is 64MB
                .config("spark.sql.adaptive.coalescePartitions.minPartitionNum", "1")
                .getOrCreate();

        // Create sample data
        Dataset<Long> df = createSampleData(spark);

        // Convert Long type to Row type for compatibility
        Dataset<Row> rowDF = df.toDF();

        // Specify the number of partitions explicitly
        int targetPartitions = 10; // Set an appropriate number of partitions
        Dataset<Row> repartitionedDF = rowDF.repartition(targetPartitions);

        // Write to local directory with specified partition count
        String outputPath = "./output/aqe_with_partition";
        repartitionedDF.write().mode(SaveMode.Overwrite).csv(outputPath);

        System.out.println("Written output to: " + outputPath);
        spark.stop();
    }

    private static Dataset<Long> createSampleData(SparkSession spark) {
        return spark.range(0L, 1000000L); // Correctly specify start and end values as Longs
    }
}
