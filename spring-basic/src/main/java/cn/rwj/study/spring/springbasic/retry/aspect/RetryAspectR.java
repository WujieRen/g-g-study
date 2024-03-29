package cn.rwj.study.spring.springbasic.retry.aspect;

import cn.rwj.study.java.retry.RetryTemplate;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author rwj
 * @since 2023/9/7
 */
@Aspect
@Component
@Slf4j
public class RetryAspectR {

    ExecutorService executorService = new ThreadPoolExecutor(3,
            5,
            1,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<Runnable>());

    @Around(value = "@annotation(retryDot)")
    public Object execute(ProceedingJoinPoint joinPoint, RetryDot retryDot) throws Exception {
        RetryTemplate retryTemplate = new RetryTemplate() {
            @SneakyThrows
            @Override
            protected Object doBiz() {
                return joinPoint.proceed();
            }
        };

        retryTemplate
                .setRetryTime(retryDot.count())
                .setSleepTime(retryDot.sleep());

        if (retryDot.asyn()) {
            return retryTemplate.submit(executorService);
        } else {
            return retryTemplate.execute();
        }
    }

}
