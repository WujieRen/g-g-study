package cn.rwj.study.spring.myspring.xiaofuge.beans.factory;

/**
 * @author rwj
 * @since 2023/11/5
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}
