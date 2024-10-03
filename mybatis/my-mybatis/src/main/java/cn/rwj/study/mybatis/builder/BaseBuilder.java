package cn.rwj.study.mybatis.builder;

import cn.rwj.study.mybatis.session.Configuration;
import cn.rwj.study.mybatis.type.TypeAliasRegistry;
import cn.rwj.study.mybatis.type.TypeHandlerRegistry;

/**
 * 构建器的基类，建造者模式
 *
 * @author rwj
 * @since 2024/9/21
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;

    protected final TypeAliasRegistry typeAliasRegistry;

    protected final TypeHandlerRegistry typeHandlerRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
        this.typeHandlerRegistry = this.configuration.getTypeHandlerRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    protected Class<?> resolveAlias(String alias) {
        return typeAliasRegistry.resolveAlias(alias);
    }

}
