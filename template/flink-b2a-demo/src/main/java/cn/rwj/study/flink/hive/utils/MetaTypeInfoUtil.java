package cn.rwj.study.flink.hive.utils;

import cn.hutool.core.convert.Convert;
import cn.rwj.study.flink.hive.model.FieldInfo;
import cn.rwj.study.flink.hive.model.Params;
import lombok.Getter;
import lombok.val;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.RowTypeInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author rwj
 * @since 2024/2/26
 */
public class MetaTypeInfoUtil {

    public static final Map<String, Class<?>> DATATYPE_MAP = new HashMap<>();

    static {
        DATATYPE_MAP.put("string", String.class);
        DATATYPE_MAP.put("int", Integer.class);
        // ...
    }

    public static RowTypeInfo getTypeInformation(Params params) {
        List<FieldInfo> sortedSinkField = MetaTypeInfoUtil.getSortedField(params);
        TypeInformation[] types = new TypeInformation[sortedSinkField.size()];
        for (int i = 0; i < sortedSinkField.size(); i++) {
            Class<?> aClass = DATATYPE_MAP.get(sortedSinkField.get(i).getFieldType().toLowerCase());
            types[i] = TypeInformation.of(aClass);
        }
        return new RowTypeInfo(types);
    }

    public static List<FieldInfo> getSortedField(Params params) {
        return getSortedField(params.getSinkInfo().getFieldInfos());
    }

    public static List<FieldInfo> getSortedField(List<FieldInfo> fieldInfos) {
        return fieldInfos
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

}
