package cn.rwj.study.spring.myspring.xiaofuge;

import cn.rwj.study.spring.myspring.xiaofuge.bean.UserDao;
import cn.rwj.study.spring.myspring.xiaofuge.bean.UserService;
import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.config.BeanReference;
import cn.rwj.study.spring.myspring.xiaofuge.beans.PropertyValue;
import cn.rwj.study.spring.myspring.xiaofuge.beans.PropertyValues;
import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.config.BeanDefinition;
import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.supprt.DefaultListableBeanFactory;
import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.xml.XmlBeanDefinitionReader;
import cn.rwj.study.spring.myspring.xiaofuge.core.io.DefaultResourceLoader;
import org.junit.Before;
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

    private DefaultResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }


}
