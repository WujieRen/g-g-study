package cn.rwj.study.spring.myspring.xiaofuge.beans.factory;

/**
 * @author rwj
 * @since 2023/11/3
 */
public interface InitializingBean {

    /**
     * Bean 处理了属性填充后调用
     *
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;

}
