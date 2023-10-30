package cn.rwj.study.spring.myspring.xiaofuge.factory.supprt;

import cn.rwj.study.spring.myspring.xiaofuge.BeanFactory;
import cn.rwj.study.spring.myspring.xiaofuge.BeansException;
import cn.rwj.study.spring.myspring.xiaofuge.factory.config.BeanDefinition;

/**
 * @author rwj
 * @since 2023/10/30
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);

}
