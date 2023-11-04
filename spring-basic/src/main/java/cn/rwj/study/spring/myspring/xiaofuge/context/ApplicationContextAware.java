package cn.rwj.study.spring.myspring.xiaofuge.context;

import cn.rwj.study.spring.myspring.xiaofuge.beans.BeansException;
import cn.rwj.study.spring.myspring.xiaofuge.beans.factory.Aware;

/**
 * @author rwj
 * @since 2023/11/4
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
