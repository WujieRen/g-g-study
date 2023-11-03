package cn.rwj.study.spring.myspring.xiaofuge.beans.factory;

import cn.rwj.study.spring.myspring.xiaofuge.beans.BeansException;
import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.config.BeanDefinition;

/**
 * Configuration interface to be implemented by most listable bean factories.
 *  In addition to {@link ConfigurableBeanFactory}, it provides facilities to analyze and modify bean definitions, and to pre-instantiate singletons.
 * @author rwj
 * @since 2023/11/1
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

}
