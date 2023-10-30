package cn.rwj.study.spring.myspring.xiaofuge;

import cn.rwj.study.spring.myspring.xiaofuge.bean.UserDao;
import cn.rwj.study.spring.myspring.xiaofuge.bean.UserService;
import cn.rwj.study.spring.myspring.xiaofuge.factory.BeanReference;
import cn.rwj.study.spring.myspring.xiaofuge.factory.PropertyValue;
import cn.rwj.study.spring.myspring.xiaofuge.factory.PropertyValues;
import cn.rwj.study.spring.myspring.xiaofuge.factory.config.BeanDefinition;
import cn.rwj.study.spring.myspring.xiaofuge.factory.supprt.DefaultListableBeanFactory;
import cn.rwj.study.spring.myspring.xiaofuge.factory.supprt.SimpleInstantiationStrategy;
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

        // 2. UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // 4. UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 5. UserService 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

}
