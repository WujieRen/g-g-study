package cn.rwj.study.spring.myspring.xiaofuge.factory.config;

/**
 * @author rwj
 * @since 2023/10/26
 */
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

}
