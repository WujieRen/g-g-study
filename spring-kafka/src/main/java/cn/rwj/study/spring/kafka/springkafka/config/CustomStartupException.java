package cn.rwj.study.spring.kafka.springkafka.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author rwj
 * @since 2023/5/4
 *
 * Bean初始化时如果报错，打印自定义提示信息
 */
@Slf4j
@Component
public class CustomStartupException implements ApplicationListener<ApplicationFailedEvent> {
    @Override
    public void onApplicationEvent(ApplicationFailedEvent applicationFailedEvent) {
        Throwable exception = applicationFailedEvent.getException();
        if(exception.getMessage().contains("kafka")) {
            log.info("Kafka配置错误，请检查Kafka配置");
        }
    }
}
