package cmn.rwj.study.spark.test.resDataFileNum;

import java.util.ArrayList;
import java.util.List;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

/**
 *
 * @author rwj
 * @since 2025/2/6
 */
public class MainWithAEREpartitionByColumn {

    public static void main(String[] args) throws AnalysisException {
        SparkSession spark = SparkSession.builder()
                .appName("WithAEREpartitionByColumn")
                .master("local[*]") // Run locally with all available cores
                .config("spark.sql.adaptive.enabled", "true")
                .config("spark.sql.adaptive.coalescePartitions.enabled", "true")
//                .config("spark.sql.adaptive.advisoryPartitionSizeInBytes", "67108864") // Default is 64MB
                .config("spark.sql.adaptive.advisoryPartitionSizeInBytes", "2MB") // Default is 64MB
                .config("spark.sql.adaptive.coalescePartitions.minPartitionNum", "1")
                .getOrCreate();

        // Define schema for the DataFrame
        StructType schema = new StructType(new StructField[]{
                DataTypes.createStructField("id", DataTypes.LongType, false),
                DataTypes.createStructField("category", DataTypes.StringType, true)
        });

        // Sample data creation using List<Row>
        List<Row> rows = new ArrayList<Row>();
        for (long i = 0; i < 1000000; i++) {
            String category = "Category_" + ((i % 10) + 1); // Simulate categories from Category_1 to Category_10
            rows.add(RowFactory.create(i, category));
        }

        // Create DataFrame from List<Row> and Schema
        Dataset<Row> df = spark.createDataFrame(rows, schema);

        // Repartition by 'category' column
        Dataset<Row> repartitionedDF = df.repartition(functions.col("category"));

        // Optionally, you can also specify the number of partitions
        // Dataset<Row> repartitionedDF = df.repartition(10, functions.col("category"));

        // Write to local directory with specified partition count based on 'category'
        String outputPath = "./output/aqe_repartition_by_column";
        repartitionedDF.write().mode(SaveMode.Overwrite).csv(outputPath);

        System.out.println("Written output to: " + outputPath);
        spark.stop();
    }

}
