package cn.rwj.study.spring.myspring.xiaofuge.event;

import cn.rwj.study.spring.myspring.xiaofuge.context.ApplicationListener;

import java.util.Date;

/**
 * @author rwj
 * @since 2023/11/5
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + " 消息；时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
