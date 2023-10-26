package cn.rwj.study.spring.fake.xiaofuge;

import cn.rwj.study.spring.fake.xiaofuge.bean.UserService;
import org.junit.Test;

/**
 * @author rwj
 * @since 2023/10/26
 */
public class ApiTest {

    @Test
    public void test_BeanFactory(){
        // 1.初始化 BeanFactory
        BeanFactory beanFactory = new BeanFactory();

        // 2.注入bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

}
