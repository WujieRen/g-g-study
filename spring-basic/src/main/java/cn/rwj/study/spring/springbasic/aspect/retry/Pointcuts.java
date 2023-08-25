package cn.rwj.study.spring.springbasic.aspect.retry;

import org.aspectj.lang.annotation.Pointcut;

/**
 * @author rwj
 * @since 2023/8/25
 */
public class Pointcuts {

    @Pointcut("execution(@cn.rwj.study.spring.springbasic.aspect.retry.Retry * *(..))")
    public void retryPointcuts() {}

}
