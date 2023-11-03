package cn.rwj.study.spring.myspring.xiaofuge.beans.factory.config;

import cn.rwj.study.spring.myspring.xiaofuge.beans.BeansException;

/**
 * 用于修改新实例化 Bean 对象的扩展点
 *
 * @author rwj
 * @since 2023/11/2
 */
public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}
