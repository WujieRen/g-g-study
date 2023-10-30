package cn.rwj.study.spring.myspring.xiaofuge.factory;

/**
 * @author rwj
 * @since 2023/10/31
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}
