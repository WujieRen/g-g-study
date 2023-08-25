package cn.rwj.study.spring.springbasic.aspect.retry;

import java.lang.annotation.*;

/**
 * @author rwj
 * @since 2023/8/25
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Retry {

    /**
     * 最多重试的次数(包括第一次调用)，默认值为5。即会在初次调用失败后最多重试4次
     * @return
     */
    int maxRetryCount() default 5;

    /**
     *判断是否需要重试的扩展点，传入的是一个方法名
     * @return
     */
    String shouldRetry() default "";

    /**
     *判断返回结果是否合法的扩展点，传入的是一个方法名
     * @return
     */
    String isOutputOK() default "";

    /**
     *在发生异常后进行处理的扩展点，传入的是一个方法名
     * @return
     */
    String handleException() default "";

    /**
     * 所有重试都失败后进行处理的扩展点，传入的是一个方法名
     * @return
     */
    String beforeExceptionalReturn() default "";

}
