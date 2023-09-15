package cn.rwj.study.spring.springbasic.idempotence.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author rwj
 * @since 2023/9/15
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {

    /**
     * 参数名，表示将从哪个参数中获取属性值。
     * 获取到的属性值将作为KEY。
     */
    String name() default "";

    /**
     * 属性，表示将获取哪个属性的值。
     */
    String field() default "";

    /**
     * 参数类型
     */
    Class type();

}
