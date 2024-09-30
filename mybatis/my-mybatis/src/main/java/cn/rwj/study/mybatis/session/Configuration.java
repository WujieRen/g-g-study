package cn.rwj.study.mybatis.session;

import cn.rwj.study.mybatis.binding.MapperRegistry;
import cn.rwj.study.mybatis.datasource.druid.DruidDataSourceFactory;
import cn.rwj.study.mybatis.datasource.pooled.PooledDataSourceFactory;
import cn.rwj.study.mybatis.datasource.unpooled.UnpooledDataSourceFactory;
import cn.rwj.study.mybatis.mappig.Environment;
import cn.rwj.study.mybatis.mappig.MappedStatement;
import cn.rwj.study.mybatis.transaction.jdbc.JdbcTransactionFactory;
import cn.rwj.study.mybatis.type.TypeAliasRegistry;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置项
 *
 * @author rwj
 * @since 2024/9/21
 */
public class Configuration {

    /**
     * 映射注册机
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * 映射的语句，存在Map里
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    //环境
    protected Environment environment;

    // 类型别名注册机
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);
    }


    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }
    
    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
