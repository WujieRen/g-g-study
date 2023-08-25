package cn.rwj.study.spring.springbasic.aspect.retry;

import java.util.Objects;

/**
 * @author rwj
 * @since 2023/8/25
 */
public class RetrySupport {

    protected boolean shouldRetry() {
        return true;
    }

    protected boolean isOutputOK(Object output) {
        return Objects.nonNull(output);
    }

    protected void handleException(Exception e) {
        System.out.println(e.getMessage());
    }

    protected void beforeExceptionalReturn() {
        System.out.println("默认的处理失败后扩展点");
    }

}
