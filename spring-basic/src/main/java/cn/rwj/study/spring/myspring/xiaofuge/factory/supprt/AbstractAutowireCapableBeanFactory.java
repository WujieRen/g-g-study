package cn.rwj.study.spring.myspring.xiaofuge.factory.supprt;

import cn.rwj.study.spring.myspring.xiaofuge.BeansException;
import cn.rwj.study.spring.myspring.xiaofuge.factory.config.BeanDefinition;

/**
 * @author rwj
 * @since 2023/10/30
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        registerSingleton(beanName, bean);
        return bean;
    }

}
