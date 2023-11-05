package cn.rwj.study.spring.myspring.xiaofuge.context;

/**
 * 事件发布者接口
 *
 * @author rwj
 * @since 2023/11/5
 */
public interface ApplicationEventPublisher {

    /**
     * Notify all listeners registered with this application of an application
     * event. Events may be framework events (such as RequestHandledEvent)
     * or application-specific events.
     *
     * @param event the event to publish
     */
    void publishEvent(ApplicationEvent event);

}
