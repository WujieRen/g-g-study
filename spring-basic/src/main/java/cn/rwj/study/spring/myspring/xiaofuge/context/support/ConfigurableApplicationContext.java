package cn.rwj.study.spring.myspring.xiaofuge.context.support;

import cn.rwj.study.spring.myspring.xiaofuge.beans.BeansException;
import cn.rwj.study.spring.myspring.xiaofuge.context.ApplicationContext;

/**
 * SPI 接口配置应用上下文 SPI interface to be implemented by most if not all application contexts.
 *  Provides facilities to configure an application context in addition
 *  to the application context client methods in the
 *  {@link cn.rwj.study.spring.myspring.xiaofuge.context.ApplicationContext} interface.
 * @author rwj
 * @since 2023/11/1
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
