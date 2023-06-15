package cn.rwj.study.spring.springbasic.BeanPostProcessorDemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rwj
 * @since 2023/6/15
 */
public abstract class AbstractAnnotationBeanPostProcessor implements BeanPostProcessor, PriorityOrdered {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        for (Field field : findAllFields(clazz)) {
            processField(bean,beanName,field);
        }
        return bean;
    }

    protected abstract void processField(Object bean, String beanName, Field field);

    //通过反射获取bean的所有field
    private List<Field> findAllFields(Class clazz) {
        List<Field> fields = new ArrayList<>();
        ReflectionUtils.doWithFields(clazz, (field) -> fields.add(field));
        return fields;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
