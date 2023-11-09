package cn.rwj.study.spring.myspring.xiaofuge.aop;

import java.lang.reflect.Method;

/**
 * Part of a {@link Pointcut}: Checks whether the target method is eligible for advice.
 *
 * @author rwj
 * @since 2023/11/6
 */
public interface MethodMatcher {

    /**
     * Perform static checking whether the given method matches.
     *
     * @return whether or not this method matches statically
     */
    boolean matches(Method method, Class<?> targetClass);

}
