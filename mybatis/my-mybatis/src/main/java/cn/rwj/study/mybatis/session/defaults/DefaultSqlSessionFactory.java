package cn.rwj.study.mybatis.session.defaults;

import cn.rwj.study.mybatis.binding.MapperRegistry;
import cn.rwj.study.mybatis.session.SqlSession;
import cn.rwj.study.mybatis.session.SqlSessionFactory;

/**
 * @author rwj
 * @since 2024/9/21
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
