package cn.rwj.study.spring.myspring.xiaofuge.beans.factory.supprt;

import cn.rwj.study.spring.myspring.xiaofuge.beans.BeansException;
import cn.rwj.study.spring.myspring.xiaofuge.core.io.Resource;
import cn.rwj.study.spring.myspring.xiaofuge.core.io.ResourceLoader;

/**
 * @author rwj
 * @since 2023/11/1
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;

}
