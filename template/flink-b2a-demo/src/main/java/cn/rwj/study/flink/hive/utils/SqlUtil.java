package cn.rwj.study.flink.hive.utils;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.rwj.study.flink.hive.model.FieldInfo;
import cn.rwj.study.flink.hive.model.Params;
import cn.rwj.study.flink.hive.model.SinkInfo;
import cn.rwj.study.flink.hive.model.SourceInfo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author rwj
 * @since 2024/2/26
 */
public class SqlUtil {

    private static final String SELECT_SOURCE_DATA_SQL = "SELECT CONCAT('{', %s, '}') AS param FROM %s";
    private static final String CRT_SINK_TABLE_SQL = "CREATE TABLE IF NOT EXISTS %s (%s) WITH ( 'connector' = 'hive' )";
    private static final String INSERT_SINK_TABLE_SQL = "INSERT OVERWRITE %s SELECT * FROM res";

    public static String getSourceSql(SourceInfo sourceInfo) {
        Assert.notEmpty(sourceInfo.getTableName(), () -> new IllegalArgumentException("参数非法！没有指定有效的数据表信息！"));

        String selectedFields = sourceInfo.getFieldInfos()
                .stream()
                .map(f -> String.format("'\"%s\":\"', cast(`%s` AS string), '\",'", f.getFieldName(), f.getFieldName()))
                .reduce((f1, f2) -> f1 + "," + f2)
                .get();
        if (StrUtil.isEmpty(selectedFields)) {
            throw new IllegalArgumentException("参数非法！没有指定有效的Source字段");
        }
        System.out.println(String.format(SELECT_SOURCE_DATA_SQL, selectedFields, sourceInfo.getTableName()));
        return String.format(SELECT_SOURCE_DATA_SQL, selectedFields, sourceInfo.getTableName());
    }

    public static String getCrtSinkTableSql(Params params) {
        List<FieldInfo> sortedField = MetaTypeInfoUtil.getSortedField(params);

        String crtTableFields = sortedField
                .stream()
                .map(f -> String.format("%s %s COMMENT '%s'", f.getFieldName(), f.getFieldType().toUpperCase(), f.getComment()))
                .reduce((f1, f2) -> f1 + ", " + f2)
                .get();
        if (StrUtil.isEmpty(crtTableFields)) {
            throw new IllegalArgumentException("参数非法！没有指定有效的Sink字段");
        }
        return String.format(CRT_SINK_TABLE_SQL, params.getSinkInfo().getTableName(), crtTableFields);
    }

    public static String getInsertSinkSql(SinkInfo sinkInfo) {
        return String.format(INSERT_SINK_TABLE_SQL, sinkInfo.getTableName());
    }

}
