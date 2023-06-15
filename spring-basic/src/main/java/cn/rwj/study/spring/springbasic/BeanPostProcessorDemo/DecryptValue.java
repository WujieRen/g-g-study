package cn.rwj.study.spring.springbasic.BeanPostProcessorDemo;

import java.lang.annotation.*;

/**
 * @author rwj
 * @since 2023/6/15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
@Documented
public @interface DecryptValue {

    String value();

}
