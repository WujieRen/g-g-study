package cn.rwj.study.spring.myspring.xiaofuge.beans.factory.supprt;

import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.config.BeanDefinition;

/**
 * @author rwj
 * @since 2023/10/30
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册 BeanDefinition
     *
     * @param beanName       Bean 名称
     * @param beanDefinition Bean 定义
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
