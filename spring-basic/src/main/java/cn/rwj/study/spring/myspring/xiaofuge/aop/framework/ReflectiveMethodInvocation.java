package cn.rwj.study.spring.myspring.xiaofuge.aop.framework;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @author rwj
 * @since 2023/11/7
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

    // 目标对象
    protected final Object target;
    // 方法
    protected final Method method;
    // 入参
    protected final Object[] arguments;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Object proceed() throws Throwable {
//        return method.invoke(target, arguments);
        System.out.println("@#@#@#@#@#@#@##@");
        Object invoke = method.invoke(target, arguments);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return invoke;
    }

    @Override
    public Object getThis() {
        return this;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
