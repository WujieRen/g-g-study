package cn.rwj.study.spring.kafka.springkafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author rwj
 * @since 2023/5/4
 */
@Slf4j
@RequestMapping("/test")
@RestController
public class TestController {

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping
    public void testProducer() {
        kafkaTemplate.send("test", "jjjjjjjjjjjjjjjjjjjjj");
    }

    @KafkaListener(id = "sampleListener", topics = "test")
    void testListener(String payload) {
        log.info("~~~~~~~~~~~~~~~~  消息变动  ~~~~~~~~~~~~~~~~");
        System.out.println("Received: " + payload);
    }

}

