package cn.rwj.study.spring.myspring.xiaofuge.aop.framework.autoProxy;

import cn.rwj.study.spring.myspring.xiaofuge.aop.AdvisedSupport;
import cn.rwj.study.spring.myspring.xiaofuge.aop.Advisor;
import cn.rwj.study.spring.myspring.xiaofuge.aop.Pointcut;
import cn.rwj.study.spring.myspring.xiaofuge.aop.TargetSource;
import cn.rwj.study.spring.myspring.xiaofuge.aop.aspectj.AspectJExpressionPointcutAdvisor;
import cn.rwj.study.spring.myspring.xiaofuge.aop.framework.ProxyFactory;
import cn.rwj.study.spring.myspring.xiaofuge.beans.BeansException;
import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.BeanFactory;
import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.BeanFactoryAware;
import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.config.InstantiationAwareBeanPostProcessor;
import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.supprt.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

/**
 * @author rwj
 * @since 2023/11/14
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {

        // 如果是基础设施类，直接返回 null，否则会陷入死循环，直到  java.lang.StackOverflowError
        if (isInfrastructureClass(beanClass)) return null;

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            if (!advisor.getPointcut().getClassFilter().matches(beanClass)) continue;

            TargetSource targetSource = null;
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            AdvisedSupport advisedSupport = new AdvisedSupport();
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);

            return new ProxyFactory(advisedSupport).getProxy();
        }

        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
