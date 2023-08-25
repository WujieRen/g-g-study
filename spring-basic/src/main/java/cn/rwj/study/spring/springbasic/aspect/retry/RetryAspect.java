package cn.rwj.study.spring.springbasic.aspect.retry;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author rwj
 * @since 2023/8/25
 */
@Component
@Aspect
public class RetryAspect extends RetrySupport {

    private static ThreadLocal<Integer> retryCounters;

    static {
        retryCounters = ThreadLocal.withInitial(() -> 0);
    }

    @Around("cn.rwj.study.spring.springbasic.aspect.retry.Pointcuts.retryPointcuts()")
    public Object retryAdvice(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(Thread.currentThread().getName() + ": 进入Advice");
        // 获取被调用的对象以及Retry注解对象
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String methodName = signature.getMethod().getName();
        Class<?>[] parameterTypes = signature.getMethod().getParameterTypes();
        Object calledObject = pjp.getTarget();
        Retry retryAnno = pjp.getTarget().getClass().getMethod(methodName, parameterTypes).getAnnotation(Retry.class);

        try {
            while (aspectShouldRetry(calledObject, retryAnno)) {
                try {
                    Object result = pjp.proceed(pjp.getArgs());
                    if (isOutputOK(result)) {
                        return result;
                    } else {
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() + ": 捕获到了异常: " + e.getMessage());
                    handleException(e);
                }
            }
        } finally {
            retryCounters.set(0);
        }

        aspectBeforeExceptionalReturn(calledObject, retryAnno);

        return null;
    }

    // 拓展点：失败返回前的处理
    private void aspectBeforeExceptionalReturn(Object calledObject, Retry retryAnno) throws Throwable {
        String beforeExceptionalReturnMethodName = retryAnno.beforeExceptionalReturn();
        if (StringUtils.isEmpty(beforeExceptionalReturnMethodName)) {
            super.beforeExceptionalReturn();
        } else {
            Method beforeExceptionalReturnMethod = calledObject.getClass().getMethod(beforeExceptionalReturnMethodName);
            beforeExceptionalReturnMethod.invoke(calledObject, new Object[] {});
        }
    }

    // 拓展点: 是否进行重试
    private boolean aspectShouldRetry(Object calledObject, Retry retryAnno) throws Throwable {
        Integer currentCount = retryCounters.get();
        retryCounters.set(currentCount + 1);
        if (++currentCount > retryAnno.maxRetryCount()) {
            return false;
        }

        boolean shouldRetry = false;
        String shouldRetryMethodName = retryAnno.shouldRetry();
        if (StringUtils.isEmpty(shouldRetryMethodName)) {
            shouldRetry = super.shouldRetry();
        } else {
            Method shouldRetryMethod = calledObject.getClass().getMethod(shouldRetryMethodName);
            shouldRetry = (boolean) shouldRetryMethod.invoke(calledObject, new Object[] {});
        }

        return shouldRetry;
    }

}
