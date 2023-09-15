package cn.rwj.study.spring.springbasic.aspect.retry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.stream.IntStream;

/**
 * @author rwj
 * @since 2023/8/25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CustomAopConfiguration.class})
public class IdempotentRetry {

    @Autowired
    private RetryService retryService;

    @Test
    public void testRetryMethod1() {
        System.out.println(retryService.doRetryBusiness());
    }

    @Test
    public void testRetryMethod2() {
        System.out.println(retryService.doRetryBusiness());
    }

    @Test
    public void testRetryMethod3() {
        System.out.println(retryService.doRetryBusiness());
    }

    @Test
    public void testConcurrentRetry() throws InterruptedException {
        IntStream.range(0, 5).forEach(i -> {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + ": 启动了");
                    System.out.println(retryService.doRetryBusiness());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        });

        Thread.sleep(10000);
    }

}
