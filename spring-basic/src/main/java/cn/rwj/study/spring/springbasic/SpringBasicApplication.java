package cn.rwj.study.spring.springbasic;

import cn.rwj.study.spring.springbasic.BeanPostProcessorDemo.AnnotationBeanPostProcessor;
import cn.rwj.study.spring.springbasic.BeanPostProcessorDemo.People;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class SpringBasicApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ac = SpringApplication.run(SpringBasicApplication.class, args);
		People people = (People) ac.getBean("people");
		people.say();
	}

	@Bean
	public BeanPostProcessor AnnotationBeanPostProcessor(ConfigurableEnvironment environment){
		return new AnnotationBeanPostProcessor(environment);
	}

}
