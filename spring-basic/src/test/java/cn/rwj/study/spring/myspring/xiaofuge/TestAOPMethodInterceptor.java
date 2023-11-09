package cn.rwj.study.spring.myspring.xiaofuge;

import cn.rwj.study.spring.myspring.xiaofuge.aop.*;
import cn.rwj.study.spring.myspring.xiaofuge.aop.aspectj.AspectJExpressionPointcut;
import cn.rwj.study.spring.myspring.xiaofuge.aop.framework.Cglib2AopProxy;
import cn.rwj.study.spring.myspring.xiaofuge.aop.framework.JdkDynamicAopProxy;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @author rwj
 * @since 2023/11/10
 */
public class TestAOPMethodInterceptor {

    @Test
    public void test_proxy_class() {
//        IUserService userService = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserService.class}, (proxy, method, args) -> "你被代理了！");
        IUserService userService = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserService.class}, (proxy, method, args) -> {
            String name = method.getName();
            return "调用了方法：" + name;
        });
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
        result = userService.register("aaaaaaaaaaaaaaaaa");
        System.out.println("测试结果：" + result);
    }
    
    @Test
    public void test_dynamic() {
        // 目标对象
        IUserService userService = new UserService();
        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* cn.rwj.study.spring.myspring.xiaofuge.aop.IUserService.queryUserInfo(..))"));

        // 代理对象(JdkDynamicAopProxy)
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_jdk.queryUserInfo());
        System.out.println("----" + proxy_jdk.register("红红火火恍恍惚惚"));
        System.out.println();

        // 代理对象(Cglib2AopProxy)
        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_cglib.register("花花"));
    }

}
