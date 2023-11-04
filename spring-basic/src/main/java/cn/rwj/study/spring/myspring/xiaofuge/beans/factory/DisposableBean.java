package cn.rwj.study.spring.myspring.xiaofuge.beans.factory;

/**
 * @author rwj
 * @since 2023/11/3
 */
public interface DisposableBean {

    void destroy() throws Exception;

}
