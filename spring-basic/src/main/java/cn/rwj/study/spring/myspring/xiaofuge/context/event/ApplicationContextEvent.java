package cn.rwj.study.spring.myspring.xiaofuge.context.event;

import cn.rwj.study.spring.myspring.xiaofuge.context.ApplicationContext;
import cn.rwj.study.spring.myspring.xiaofuge.context.ApplicationEvent;

/**
 * @author rwj
 * @since 2023/11/5
 */
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * Get the <code>ApplicationContext</code> that the event was raised for.
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }

}
