package cn.rwj.study.spring.myspring.xiaofuge;

import cn.rwj.study.spring.myspring.xiaofuge.bean.UserService;
import cn.rwj.study.spring.myspring.xiaofuge.factory.config.BeanDefinition;
import cn.rwj.study.spring.myspring.xiaofuge.factory.supprt.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * @author rwj
 * @since 2023/10/26
 */
public class ApiTest {

    @Test
    public void test_BeanFactory(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 4.第二次获取 bean from Singleton
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();
    }

}
