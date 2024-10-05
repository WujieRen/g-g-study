package cn.rwj.study.mybatis.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author rwj
 * @since 2024/10/5
 */
public class StringTypeHandler extends BaseTypeHandler<String> {

    @Override
    protected void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter);
    }
    
}
