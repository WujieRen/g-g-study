package cn.rwj.study.spring.myspring.xiaofuge.context.support;

import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.supprt.DefaultListableBeanFactory;
import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author rwj
 * @since 2023/11/1
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();

}
