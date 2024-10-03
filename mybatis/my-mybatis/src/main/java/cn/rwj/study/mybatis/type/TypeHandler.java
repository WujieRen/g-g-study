package cn.rwj.study.mybatis.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 类型处理器
 *
 * @author rwj
 * @since 2024/10/3
 */
public interface TypeHandler<T> {

    /**
     * 设置参数
     */
    void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException;

}
