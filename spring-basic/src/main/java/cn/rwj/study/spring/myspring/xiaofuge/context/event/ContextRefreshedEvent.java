package cn.rwj.study.spring.myspring.xiaofuge.context.event;

/**
 * @author rwj
 * @since 2023/11/5
 */
public class ContextRefreshedEvent extends ApplicationContextEvent{

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
