package cn.rwj.study.mybatis.builder;

import cn.rwj.study.mybatis.mappig.BoundSql;
import cn.rwj.study.mybatis.mappig.ParameterMapping;
import cn.rwj.study.mybatis.mappig.SqlSource;
import cn.rwj.study.mybatis.session.Configuration;

import java.util.List;

/**
 * @author rwj
 * @since 2024/10/3
 */
public class StaticSqlSource implements SqlSource {

    private String sql;
    private List<ParameterMapping> parameterMappings;
    private Configuration configuration;

    public StaticSqlSource(Configuration configuration, String sql) {
        this(configuration, sql, null);
    }

    public StaticSqlSource(Configuration configuration, String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.configuration = configuration;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return new BoundSql(configuration, sql, parameterMappings, parameterObject);
    }
}
