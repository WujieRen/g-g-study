package cn.rwj.study.spring.myspring.xiaofuge.aop.framework;

/**
 * Delegate interface for a configured AOP proxy, allowing for the creation
 * of actual proxy objects.
 * <p>
 * Out-of-the-box implementations are available for JDK dynamic proxies
 * and for CGLIB proxies, as applied by DefaultAopProxyFactory
 *
 * @author rwj
 * @since 2023/11/7
 */
public interface AopProxy {

    Object getProxy();

}
