package cn.rwj.study.spring.myspring.xiaofuge.autoscan;

import cn.rwj.study.spring.myspring.xiaofuge.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author rwj
 * @since 2023/11/19
 */
public class TestAutoScan {

    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:autoscan/spring-scan.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + System.identityHashCode(userService));
        userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + System.identityHashCode(userService));
    }

}
