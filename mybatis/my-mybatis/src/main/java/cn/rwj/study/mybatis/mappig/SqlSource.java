package cn.rwj.study.mybatis.mappig;

/**
 * SQL源码
 *
 * @author rwj
 * @since 2024/10/3
 */
public interface SqlSource {

    BoundSql getBoundSql(Object parameterObject);
    
}
