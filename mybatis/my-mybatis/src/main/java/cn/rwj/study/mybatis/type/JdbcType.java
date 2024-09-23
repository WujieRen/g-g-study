package cn.rwj.study.mybatis.type;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * JDBC类型枚举
 *
 * @author rwj
 * @since 2024/9/23
 */
public enum JdbcType {

    INTEGER(Types.INTEGER),

    FLOAT(Types.FLOAT),

    DOUBLE(Types.DOUBLE),

    DECIMAL(Types.DECIMAL),

    VARCHAR(Types.VARCHAR),

    TIMESTAMP(Types.TIMESTAMP);

    public final int TYPE_CODE;

    private static final Map<Integer, JdbcType> CODE_LOOK_UP = new HashMap<>();

    // 就将数字对应的枚举型放入 HashMap
    static {
        for (JdbcType type : JdbcType.values()) {
            CODE_LOOK_UP.put(type.TYPE_CODE, type);
        }
    }

    JdbcType(int code) {
        this.TYPE_CODE = code;
    }

    public static JdbcType forCode(int code) {
        return CODE_LOOK_UP.get(code);
    }

}
