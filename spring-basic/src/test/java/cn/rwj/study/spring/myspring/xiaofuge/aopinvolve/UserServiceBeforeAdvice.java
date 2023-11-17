package cn.rwj.study.spring.myspring.xiaofuge.aopinvolve;

import cn.rwj.study.spring.myspring.xiaofuge.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author rwj
 * @since 2023/11/15
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) {
        System.out.println("拦截方法：" + method.getName());
    }

}
