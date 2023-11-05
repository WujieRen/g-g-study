package cn.rwj.study.spring.myspring.xiaofuge;

import cn.rwj.study.spring.myspring.xiaofuge.context.support.ClassPathXmlApplicationContext;
import cn.rwj.study.spring.myspring.xiaofuge.event.CustomEvent;
import org.junit.Test;

/**
 * @author rwj
 * @since 2023/11/5
 */
public class TestEvent {

    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:event.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));

        applicationContext.registerShutdownHook();
    }

}
