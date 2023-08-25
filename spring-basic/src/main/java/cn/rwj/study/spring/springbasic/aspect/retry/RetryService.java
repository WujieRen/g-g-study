package cn.rwj.study.spring.springbasic.aspect.retry;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author rwj
 * @since 2023/8/25
 */
@Service
public class RetryService {
    private AtomicInteger retryCount = new AtomicInteger(0);

    @Retry(maxRetryCount = 2, beforeExceptionalReturn = "extendedBeforeExceptionalReturn")
    public String doRetryBusiness() {
        if (retryCount.getAndIncrement() < 4) {
            throw new RuntimeException(Thread.currentThread().getName() + ": 结果获取失败");
        }
        return Thread.currentThread().getName() + ": 这是最终结果";
    }

    public void extendedBeforeExceptionalReturn() {
        System.out.println(Thread.currentThread().getName() + ": 自定义的处理失败后扩展点");
    }

}
