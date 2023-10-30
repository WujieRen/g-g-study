package cn.rwj.study.spring.myspring.xiaofuge.factory.supprt;

import cn.rwj.study.spring.myspring.xiaofuge.BeansException;
import cn.rwj.study.spring.myspring.xiaofuge.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author rwj
 * @since 2023/10/30
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;

}
