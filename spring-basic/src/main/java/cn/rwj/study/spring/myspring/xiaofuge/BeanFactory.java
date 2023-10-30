package cn.rwj.study.spring.myspring.xiaofuge;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author rwj
 * @since 2023/10/26
 */
public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

}
