package cn.rwj.study.spring.myspring.xiaofuge.event;

import cn.rwj.study.spring.myspring.xiaofuge.context.ApplicationListener;
import cn.rwj.study.spring.myspring.xiaofuge.context.event.ContextRefreshedEvent;

/**
 * @author rwj
 * @since 2023/11/5
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }
}
