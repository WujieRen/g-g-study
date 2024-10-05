package cn.rwj.study.mybatis.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Long类型处理器
 *
 * @author rwj
 * @since 2024/10/5
 */
public class LongTypeHandler extends BaseTypeHandler<Long> {
    @Override
    protected void setNonNullParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, parameter);
    }
}
