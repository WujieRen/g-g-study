package cn.rwj.study.spring.springbasic.aspect.retry;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author rwj
 * @since 2023/8/25
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "cn.rwj.study.spring.springbasic.aspect.retry")
public class CustomAopConfiguration {
}
