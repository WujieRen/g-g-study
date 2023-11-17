package cn.rwj.study.spring.myspring.xiaofuge.aop.framework;


import cn.rwj.study.spring.myspring.xiaofuge.aop.AdvisedSupport;

/**
 * @author rwj
 * @since 2023/11/14
 */
public class ProxyFactory {

    private final AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }

        return new JdkDynamicAopProxy(advisedSupport);
    }

}
