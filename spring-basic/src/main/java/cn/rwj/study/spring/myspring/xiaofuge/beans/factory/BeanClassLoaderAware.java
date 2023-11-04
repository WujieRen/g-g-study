package cn.rwj.study.spring.myspring.xiaofuge.beans.factory;

/**
 *  Callback that allows a bean to be aware of the bean
 *   {@link ClassLoader class loader}; that is, the class loader used by the
 *   present bean factory to load bean classes.
 * @author rwj
 * @since 2023/11/4
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);

}
