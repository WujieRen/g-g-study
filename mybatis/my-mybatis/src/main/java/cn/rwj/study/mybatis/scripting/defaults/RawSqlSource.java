package cn.rwj.study.mybatis.scripting.defaults;

import cn.rwj.study.mybatis.builder.SqlSourceBuilder;
import cn.rwj.study.mybatis.mappig.BoundSql;
import cn.rwj.study.mybatis.mappig.SqlSource;
import cn.rwj.study.mybatis.scripting.xmltags.DynamicContext;
import cn.rwj.study.mybatis.scripting.xmltags.SqlNode;
import cn.rwj.study.mybatis.session.Configuration;

import java.util.HashMap;

/**
 * 原始SQL源码，比 DynamicSqlSource 动态SQL处理快
 *
 * @author rwj
 * @since 2024/10/3
 */
public class RawSqlSource implements SqlSource {

    private final SqlSource sqlSource;

    public RawSqlSource(Configuration configuration, SqlNode rootSqlNode, Class<?> parameterType) {
        this(configuration, getSql(configuration, rootSqlNode), parameterType);
    }

    public RawSqlSource(Configuration configuration, String sql, Class<?> parameterType) {
        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
        Class<?> clazz = parameterType == null ? Object.class : parameterType;
        sqlSource = sqlSourceParser.parse(sql, clazz, new HashMap<>());
    }


    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return sqlSource.getBoundSql(parameterObject);
    }

    private static String getSql(Configuration configuration, SqlNode rootSqlNode) {
        DynamicContext context = new DynamicContext(configuration, null);
        rootSqlNode.apply(context);
        return context.getSql();
    }

}
