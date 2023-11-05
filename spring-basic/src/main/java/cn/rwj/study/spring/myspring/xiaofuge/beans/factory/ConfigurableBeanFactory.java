package cn.rwj.study.spring.myspring.xiaofuge.beans.factory;

import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.config.BeanPostProcessor;

/**
 * Configuration interface to be implemented by most bean factories. Provides
 * facilities to configure a bean factory, in addition to the bean factory
 * client methods in the {@link cn.rwj.study.spring.myspring.xiaofuge.beans.factory.BeanFactory} interface.
 *
 * @author rwj
 * @since 2023/11/2
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();
}
