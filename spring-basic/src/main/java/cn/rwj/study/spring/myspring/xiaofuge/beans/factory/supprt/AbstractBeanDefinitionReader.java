package cn.rwj.study.spring.myspring.xiaofuge.beans.factory.supprt;

import cn.rwj.study.spring.myspring.xiaofuge.core.io.DefaultResourceLoader;
import cn.rwj.study.spring.myspring.xiaofuge.core.io.ResourceLoader;

/**
 * @author rwj
 * @since 2023/11/1
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
