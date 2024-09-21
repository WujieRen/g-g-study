package cn.rwj.study.mybatis.builder;

import cn.rwj.study.mybatis.session.Configuration;

/**
 * 构建器的基类，建造者模式
 *
 * @author rwj
 * @since 2024/9/21
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

}
