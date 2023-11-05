package cn.rwj.study.spring.myspring.xiaofuge.event;

import cn.rwj.study.spring.myspring.xiaofuge.context.ApplicationListener;
import cn.rwj.study.spring.myspring.xiaofuge.context.event.ContextClosedEvent;

/**
 * @author rwj
 * @since 2023/11/5
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }
}
