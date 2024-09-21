package cn.rwj.study.mybatis.binding;

import cn.rwj.study.mybatis.session.SqlSession;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author rwj
 * @since 2024/9/21
 */
public class MapperProxyFactory<T> {

    private Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }

}
