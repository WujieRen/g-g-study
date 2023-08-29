package cn.rwj.study.flink.connector.http;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.configuration.ConfigOption;
import org.apache.flink.configuration.ConfigOptions;
import org.apache.flink.configuration.ReadableConfig;
import org.apache.flink.table.catalog.CatalogTable;
import org.apache.flink.table.connector.format.DecodingFormat;
import org.apache.flink.table.connector.source.DynamicTableSource;
import org.apache.flink.table.data.RowData;
import org.apache.flink.table.factories.DeserializationFormatFactory;
import org.apache.flink.table.factories.DynamicTableSourceFactory;
import org.apache.flink.table.factories.Factory;
import org.apache.flink.table.factories.FactoryUtil;
import org.apache.flink.table.types.DataType;

import java.util.HashSet;
import java.util.Set;

/**
 * 1、实现 DynamicTableSourceFactory 接口，添加必填属性 http.url 和 http.interval 的 ConfigOption， 创建 HttpDynamicTableSource
 * 2、默认情况下，Flink 使用 Java 的服务提供者接口 (SPI)发现 TableSourceFactory 的实例，
 *      所以需要在 META-INF/services/org.apache.flink.table.factories.Factory 中添加 HttpDynamicTableFactory 的全限定类名
 * @author rwj
 * @since 2023/8/21
 */
public class HttpDynamicTableFactory implements DynamicTableSourceFactory {

    // define all options statically
    public static final ConfigOption<String> URL = ConfigOptions.key("http.url")
            .stringType()
            .noDefaultValue();

    public static final ConfigOption<Long> INTERVAL = ConfigOptions.key("http.interval")
            .longType()
            .noDefaultValue();

    /**
     * Returns a unique identifier among same factory interfaces.
     *
     * <p>For consistency, an identifier should be declared as one lower case word (e.g. {@code
     * kafka}). If multiple factories exist for different versions, a version should be appended
     * using "-" (e.g. {@code elasticsearch-7}).
     */
    @Override
    public String factoryIdentifier() {
        return "http"; // used for matching to `connector = '...'`
    }

    /**
     * Returns a set of {@link ConfigOption} that an implementation of this factory requires in
     * addition to {@link #optionalOptions()}.
     *
     * <p>See the documentation of {@link Factory} for more information.
     */
    @Override
    public Set<ConfigOption<?>> requiredOptions() {
        final Set<ConfigOption<?>> options = new HashSet<>();
        options.add(URL);
        options.add(INTERVAL);
        options.add(FactoryUtil.FORMAT); // use pre-defined option for format
        return options;
    }

    /**
     * Returns a set of {@link ConfigOption} that an implementation of this factory consumes in
     * addition to {@link #requiredOptions()}.
     *
     * <p>See the documentation of {@link Factory} for more information.
     */
    @Override
    public Set<ConfigOption<?>> optionalOptions() {
        final Set<ConfigOption<?>> options = new HashSet<>();
        // no optional option
//        options.add(BYTE_DELIMITER);
        return options;
    }

    /**
     * Creates a {@link DynamicTableSource} instance from a {@link CatalogTable} and additional
     * context information.
     *
     * <p>An implementation should perform validation and the discovery of further (nested)
     * factories in this method.
     */
    @Override
    public DynamicTableSource createDynamicTableSource(Context context) {
        // either implement your custom validation logic here ...
        // or use the provided helper utility
        final FactoryUtil.TableFactoryHelper helper = FactoryUtil.createTableFactoryHelper(this, context);

        // discover a suitable decoding format
        final DecodingFormat<DeserializationSchema<RowData>> decodingFormat = helper.discoverDecodingFormat(
                DeserializationFormatFactory.class,
                FactoryUtil.FORMAT);

        // validate all options
        helper.validate();

        // get the validated options
        final ReadableConfig options = helper.getOptions();
        final String url = options.get(URL);
        final long interval = options.get(INTERVAL);

        // derive the produced data type (excluding computed columns) from the catalog table
        final DataType producedDataType =
                context.getCatalogTable().getResolvedSchema().toPhysicalRowDataType();

        // create and return dynamic table source
        return new HttpDynamicTableSource(url, interval, decodingFormat, producedDataType);
    }

}
