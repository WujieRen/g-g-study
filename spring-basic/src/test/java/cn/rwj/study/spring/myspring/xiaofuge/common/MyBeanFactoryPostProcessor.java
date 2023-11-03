package cn.rwj.study.spring.myspring.xiaofuge.common;

import cn.rwj.study.spring.myspring.xiaofuge.beans.BeansException;
import cn.rwj.study.spring.myspring.xiaofuge.beans.PropertyValue;
import cn.rwj.study.spring.myspring.xiaofuge.beans.PropertyValues;
import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.ConfigurableListableBeanFactory;
import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.config.BeanDefinition;
import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author rwj
 * @since 2023/11/2
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }

}
