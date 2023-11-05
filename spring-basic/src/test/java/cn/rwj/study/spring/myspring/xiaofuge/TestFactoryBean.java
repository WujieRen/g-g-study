package cn.rwj.study.spring.myspring.xiaofuge;

import cn.rwj.study.spring.myspring.xiaofuge.bean.IUserDao;
import cn.rwj.study.spring.myspring.xiaofuge.bean.UserService;
import cn.rwj.study.spring.myspring.xiaofuge.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author rwj
 * @since 2023/11/5
 */
public class TestFactoryBean {

    @Test
    public void test_prototype() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService01 = applicationContext.getBean("userService", UserService.class);
        UserService userService02 = applicationContext.getBean("userService", UserService.class);

        // 3. 配置 scope="prototype/singleton"
        System.out.println(userService01);
        System.out.println(userService02);


        System.out.println(userService01.getUserDao());

        IUserDao userDao = userService01.getUserDao();
        System.out.println(userDao.queryUserName("10001"));

//        IUserDao userDao = applicationContext.getBean("proxyUserDao", IUserDao.class);
//        System.out.println(userDao.queryUserName("10001"));


    }


}
