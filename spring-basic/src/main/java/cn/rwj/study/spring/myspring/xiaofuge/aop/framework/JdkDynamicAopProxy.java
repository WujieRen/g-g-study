package cn.rwj.study.spring.myspring.xiaofuge.aop.framework;

import cn.rwj.study.spring.myspring.xiaofuge.aop.AdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author rwj
 * @since 2023/11/7
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private AdvisedSupport advivce;

    public JdkDynamicAopProxy(AdvisedSupport advivce) {
        this.advivce = advivce;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), advivce.getTargetSource().getTargetClass(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (advivce.getMethodMatcher().matches(method, advivce.getTargetSource().getTarget().getClass())) {
            return advivce.getMethodInterceptor().invoke(new ReflectiveMethodInvocation(advivce.getTargetSource().getTarget(), method, args));
        }
        return method.invoke(advivce.getTargetSource().getTarget(), args);
    }
}
