package cn.rwj.study.spring.myspring.xiaofuge.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author rwj
 * @since 2023/11/1
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}
