package cn.rwj.study.spring.myspring.xiaofuge.context;

import java.util.EventObject;

/**
 * Class to be extended by all application events. Abstract as it
 *   doesn't make sense for generic events to be published directly.
 *
 * @author rwj
 * @since 2023/11/5
 */
public class ApplicationEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
