package cn.rwj.study.spring.myspring.xiaofuge.core.io;

/**
 * @author rwj
 * @since 2023/11/1
 */
public interface ResourceLoader {

    /**
     * Pseudo URL prefix for loading from the class path: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
