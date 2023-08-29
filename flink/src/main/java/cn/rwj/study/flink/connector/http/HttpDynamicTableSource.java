package cn.rwj.study.flink.connector.http;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.table.connector.ChangelogMode;
import org.apache.flink.table.connector.format.DecodingFormat;
import org.apache.flink.table.connector.source.*;
import org.apache.flink.table.data.RowData;
import org.apache.flink.table.types.DataType;
import org.apache.flink.types.RowKind;

/**
 * HttpDynamicTableSource 实现 ScanTableSource，
 * 接收 table properties 的属性，从 format 创建匹配的 反序列化器，创建 HttpSource
 * @author rwj
 * @since 2023/8/21
 */
public class HttpDynamicTableSource implements ScanTableSource {

    private final String url;
    private final long interval;
    private final DecodingFormat<DeserializationSchema<RowData>> decodingFormat;
    private final DataType producedDataType;

    public HttpDynamicTableSource(
            String hostname,
            long interval,
            DecodingFormat<DeserializationSchema<RowData>> decodingFormat,
            DataType producedDataType) {
        this.url = hostname;
        this.interval = interval;
        this.decodingFormat = decodingFormat;
        this.producedDataType = producedDataType;
    }


    /**
     * Returns the set of changes that the planner can expect during runtime.
     *
     * @see RowKind
     */
    @Override
    public ChangelogMode getChangelogMode() {
        // in our example the format decides about the changelog mode
        // but it could also be the source itself
        return decodingFormat.getChangelogMode();
    }

    /**
     * Returns a provider of runtime implementation for reading the data.
     *
     * <p>There might exist different interfaces for runtime implementation which is why {@link
     * ScanRuntimeProvider} serves as the base interface. Concrete {@link ScanRuntimeProvider}
     * interfaces might be located in other Flink modules.
     *
     * <p>Independent of the provider interface, the table runtime expects that a source
     * implementation emits internal data structures (see {@link
     * org.apache.flink.table.data.RowData} for more information).
     *
     * <p>The given {@link ScanContext} offers utilities by the planner for creating runtime
     * implementation with minimal dependencies to internal data structures.
     *
     * <p>{@link SourceProvider} is the recommended core interface. {@code SourceFunctionProvider}
     * in {@code flink-table-api-java-bridge} and {@link InputFormatProvider} are available for
     * backwards compatibility.
     *
     * @see SourceProvider
     */
    @Override
    public ScanRuntimeProvider getScanRuntimeProvider(ScanContext runtimeProviderContext) {
        // create runtime classes that are shipped to the cluster
        final DeserializationSchema<RowData> deserializer = decodingFormat.createRuntimeDecoder(runtimeProviderContext, producedDataType);

        final SourceFunction<RowData> sourceFunction = new HttpSource(url, interval, deserializer);
        return SourceFunctionProvider.of(sourceFunction, false);
    }

    /**
     * Creates a copy of this instance during planning. The copy should be a deep copy of all
     * mutable members.
     */
    @Override
    public DynamicTableSource copy() {
        return new HttpDynamicTableSource(url, interval, decodingFormat, producedDataType);
    }

    /** Returns a string that summarizes this source for printing to a console or log. */
    @Override
    public String asSummaryString() {
        return "Http Table Source";
    }

}
