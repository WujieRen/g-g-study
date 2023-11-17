package cn.rwj.study.spring.myspring.xiaofuge.aopinvolve;

import cn.rwj.study.spring.myspring.xiaofuge.aop.AdvisedSupport;
import cn.rwj.study.spring.myspring.xiaofuge.aop.Advisor;
import cn.rwj.study.spring.myspring.xiaofuge.aop.TargetSource;
import cn.rwj.study.spring.myspring.xiaofuge.aop.UserServiceInterceptor;
import cn.rwj.study.spring.myspring.xiaofuge.aop.aspectj.AspectJExpressionPointcut;
import cn.rwj.study.spring.myspring.xiaofuge.aop.framework.ProxyFactory;
import cn.rwj.study.spring.myspring.xiaofuge.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import cn.rwj.study.spring.myspring.xiaofuge.aop.framework.autoProxy.DefaultAdvisorAutoProxyCreator;
import cn.rwj.study.spring.myspring.xiaofuge.context.support.ClassPathXmlApplicationContext;
import org.junit.Before;
import org.junit.Test;

/**
 * @author rwj
 * @since 2023/11/15
 */
public class TestAopInvolve {

    @Test
    public void test_aop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:aop-involve.xml");

        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

    @Test
    public void t() {
        System.out.println(Advisor.class.isAssignableFrom(DefaultAdvisorAutoProxyCreator.class));
    }

    private AdvisedSupport advisedSupport;

//    @Before
    public void init() {
        // 目标对象
        IUserService userService = new UserService();
        // 组装代理信息
        advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* cn.rwj.study.spring.myspring.xiaofuge.aopinvolve.IUserService.*(..))"));
    }

    @Test
    public void test_beforeAdvice() {

        UserServiceBeforeAdvice beforeAdvice = new UserServiceBeforeAdvice();
        MethodBeforeAdviceInterceptor interceptor = new MethodBeforeAdviceInterceptor(beforeAdvice);
        advisedSupport.setMethodInterceptor(interceptor);

        IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
        System.out.println("测试结果：" + proxy.queryUserInfo());
    }

}