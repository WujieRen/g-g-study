package cn.rwj.study.spring.myspring.xiaofuge.aop.framework;

import cn.rwj.study.spring.myspring.xiaofuge.aop.AdvisedSupport;
import cn.rwj.study.spring.myspring.xiaofuge.aop.MethodMatcher;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;

/**
 * @author rwj
 * @since 2023/11/7
 */
public class Cglib2AopProxy implements AopProxy {

    private AdvisedSupport advised;

    public Cglib2AopProxy(AdvisedSupport adviceSupport) {
        this.advised = adviceSupport;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        Class<?> aClass = advised.getTargetSource().getTarget().getClass();
        aClass = ClassUtils.isCglibProxyClass(aClass) ? aClass.getSuperclass() : aClass;
        enhancer.setSuperclass(aClass);
        enhancer.setInterfaces(advised.getTargetSource().getTargetClass());
        /*enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                CglibMethodInvocation cglibMethodInvocation = new CglibMethodInvocation(obj, method, args, proxy);
//                if (advised.getMethodMatcher().matches(method, aClass) {  //TODO: 这里的 aClass 为何必须是 final 的？
                if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
                    advised.getMethodInterceptor().invoke(cglibMethodInvocation);
                }
                return cglibMethodInvocation.proceed();
            }
        });*/
        enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
        return enhancer.create();
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor {

        private AdvisedSupport advised;

        public DynamicAdvisedInterceptor(AdvisedSupport advisedSupport) {
            this.advised = advisedSupport;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            //TODO: 这么写的话会导致 StackOverflowError ， 为何？
            CglibMethodInvocation cglibMethodInvocation = new CglibMethodInvocation(obj, method, args, proxy);
//            CglibMethodInvocation cglibMethodInvocation = new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, args, proxy);
            if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
                advised.getMethodInterceptor().invoke(cglibMethodInvocation);
            }
            return cglibMethodInvocation.proceed();
        }

    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

        private final MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method, Object[] arguments, MethodProxy methodProxy) {
            super(target, method, arguments);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(this.target, this.arguments);
        }

    }

}
