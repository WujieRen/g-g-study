package cn.rwj.study.spring.myspring.xiaofuge.context.support;

import cn.rwj.study.spring.myspring.xiaofuge.beans.BeansException;
import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.ConfigurableListableBeanFactory;
import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.supprt.DefaultListableBeanFactory;

/**
 * @author rwj
 * @since 2023/11/1
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

}
