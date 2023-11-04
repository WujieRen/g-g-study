package cn.rwj.study.spring.myspring.xiaofuge.beans.factory;

import cn.rwj.study.spring.myspring.xiaofuge.beans.BeansException;

/**
 * 实现此接口，既能感知到所属的 BeanFactory
 *
 * @author rwj
 * @since 2023/11/4
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
