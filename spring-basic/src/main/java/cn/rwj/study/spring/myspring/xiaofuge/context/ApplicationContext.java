package cn.rwj.study.spring.myspring.xiaofuge.context;

import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.HierarchicalBeanFactory;
import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.ListableBeanFactory;
import cn.rwj.study.spring.myspring.xiaofuge.core.io.ResourceLoader;

/**
 * 应用上下文接口 Central interface to provide configuration for an application.
 *  This is read-only while the application is running, but may be
 *  reloaded if the implementation supports this.
 * @author rwj
 * @since 2023/11/1
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
