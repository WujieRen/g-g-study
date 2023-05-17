package cn.rwj.study.flink.kafka;

import org.apache.flink.api.common.serialization.SimpleStringSchema;

import java.util.Objects;

/**
 * @author rwj
 * @since 2023/5/10
 */
public class MySimpleStringSchema extends SimpleStringSchema {
    @Override
    public String deserialize(byte[] message) {
        if(Objects.nonNull(message)) {
            return super.deserialize(message);
        }
        return "";
    }
}
