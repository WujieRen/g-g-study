package cn.rwj.study.spring.myspring.xiaofuge.context.support;

import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.BeanFactory;
import cn.rwj.study.spring.myspring.xiaofuge.context.ApplicationEvent;
import cn.rwj.study.spring.myspring.xiaofuge.context.ApplicationListener;
import cn.rwj.study.spring.myspring.xiaofuge.context.event.AbstractApplicationEventMulticaster;
import cn.rwj.study.spring.myspring.xiaofuge.context.event.ApplicationEventMulticaster;

/**
 * Simple implementation of the {@link ApplicationEventMulticaster} interface.
 *
 * @author rwj
 * @since 2023/11/5
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }

}
