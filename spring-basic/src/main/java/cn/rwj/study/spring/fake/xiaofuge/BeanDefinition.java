package cn.rwj.study.spring.fake.xiaofuge;

/**
 * @author rwj
 * @since 2023/10/26
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

}
