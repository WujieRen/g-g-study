package cn.rwj.study.spring.myspring.xiaofuge.context.annotation;

import cn.hutool.core.util.ClassUtil;
import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.config.BeanDefinition;
import cn.rwj.study.spring.myspring.xiaofuge.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A component provider that scans the classpath from a base package. It then
 *   applies exclude and include filters to the resulting classes to find candidates.
 *
 * @author rwj
 * @since 2023/11/19
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        LinkedHashSet<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }

}
