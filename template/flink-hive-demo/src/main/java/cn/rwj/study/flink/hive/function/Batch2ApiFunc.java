package cn.rwj.study.flink.hive.function;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import cn.rwj.study.flink.hive.model.ApiInfo;
import cn.rwj.study.flink.hive.model.FieldInfo;
import cn.rwj.study.flink.hive.model.Params;
import cn.rwj.study.flink.hive.model.SinkInfo;
import cn.rwj.study.flink.hive.utils.HtpUtil;
import cn.rwj.study.flink.hive.utils.MetaTypeInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.types.Row;
import org.apache.flink.util.Collector;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author rwj
 * @since 2024/2/26
 */
@Slf4j
public class Batch2ApiFunc implements FlatMapFunction<Row, Row> {

    private ApiInfo apiInfo;
    private SinkInfo sinkInfo;

    public Batch2ApiFunc(Params params) {
        this.apiInfo = params.getApiInfo();
        this.sinkInfo = params.getSinkInfo();
    }

    @Override
    public void flatMap(Row value, Collector out) throws IOException {
        log.error(value.getField("param").toString());

        String param = JSONUtil.toJsonStr(value.getField("param").toString());

        String response = HtpUtil.callApi(apiInfo, param);
        if (null == response) return;
        if (JSONUtil.isTypeJSONObject(response)) {
            response = String.format("[%s]", response);
        }

        JSONUtil.parseArray(response).forEach(item -> {
            out.collect(getRow(value, item));
        });
    }

    private Row getRow(Row row, Object responseStr) {
        HashMap sourceFieldValueMap = JSONUtil.toBean(row.getField(0).toString(), HashMap.class);
        HashMap sinkFieldValMap = JSONUtil.toBean(responseStr.toString(), HashMap.class);
        List<FieldInfo> sortedSinkField = MetaTypeInfoUtil.getSortedField(sinkInfo.getFieldInfos());

        int sinkTabFieldNum = sortedSinkField.size();
        Object[] objects = new Object[sinkTabFieldNum];
        for (int i = 0; i < sortedSinkField.size(); i++) {
            FieldInfo fieldInfo = sortedSinkField.get(i);
            String fieldName = fieldInfo.getFieldName();
            Object val = sinkFieldValMap.getOrDefault(fieldName, sourceFieldValueMap.getOrDefault(fieldName, null));
            Class<?> toClass = MetaTypeInfoUtil.DATATYPE_MAP.get(fieldInfo.getFieldType().toLowerCase());
            objects[i] = Convert.convert(toClass, val);
        }

        return Row.of(objects);
    }

}
