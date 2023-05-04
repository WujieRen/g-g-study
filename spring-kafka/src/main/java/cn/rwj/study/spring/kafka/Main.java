package cn.rwj.study.spring.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Springboot 集成 Kafka with Kerberos
 * 两种方式：
 *  1. Java src 原生代码集成，见 {@link cn.rwj.study.spring.kafka.javasrc.Test Test}
 *  2. 使用 Spring-kafka 集成；这种方式集成有两种方法：
 *      1. 直接配置文件配置 application.yml
 *      2. 自定义 KafkaJaasLoginModuleInitializer
 *
 *
 * 原理：
 *  1. Kafka(Zookeeper) with Kerberos 环境准备好
 *  2. keytab、krb5.conf、jaas.conf 需要拉到本地
 *      注意：
 *          - krb5.conf 文件中不能包含Server端路径 includedir /etc/krb5.conf.d/
 *          - keytab 文件中，keyTab 值要配置成本地keyTab文件路径，如：keyTab="D:/kafka.keytab"
 *  3. 其他配置，略；详见 application.yml 或者 {@link cn.rwj.study.spring.kafka.springkafka.config.FirstWay FirstWay}
 *
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        systemPropertisConfig();
        SpringApplication.run(Main.class, args);
    }

    /**
     * 系统环境属性 --- 设置
     *
     * 注:因为是系统参数，多出地方都要使用；所以直接写在启动类里面
     *
     * 注:设置系统环境属性 的 方式较多，这只是其中的一种
     *
     * @author JustryDeng
     * @date 2019/2/24 10:31
     */
    private static void systemPropertisConfig(){
        System.setProperty("java.security.krb5.conf", "D:/test.conf");
        System.setProperty("java.security.auth.login.config", "D:/kafka_server_jaas.conf");
    }


    /*@Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServers);
        configs.put(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        configs.put(SaslConfigs.SASL_MECHANISM, SaslConfigs.GSSAPI_MECHANISM);
        configs.put("sasl.kerberos.service.name", "kafka");
        configs.put("kerberos.domain.name", "HADOOP.COM");
        return new KafkaAdmin(configs);
    }*/

   /* @Bean
    public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> kafkaConsumerFactory, KafkaTemplate<String, String> template) {
        System.out.println(boostrapServers);
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, kafkaConsumerFactory);
        factory.setErrorHandler(new SeekToCurrentErrorHandler(new DeadLetterPublishingRecoverer(template),
                new FixedBackOff(0L, 2))); // dead-letter after 3 tries
        return factory;
    }*/

    /*
    @Bean
    public ConsumerFactory<Object, Object> consumerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put("security.protocol", "SASL_PLAINTEXT");
        configs.put("kerberos.domain.name", "HADOOP.COM");
        configs.put("bootstrap.servers", "m1.rwj.cn:9092");
        configs.put("sasl.kerberos.service.name", "kafka");
        configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        configs.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        configs.put("sasl.keystore.password", "kafka");
        configs.put("sssl.keystore.location", "D:/kafka.keytab");

        configs.put("sasl.login.class", "com.sun.security.auth.module.Krb5LoginModule");
        return new DefaultKafkaConsumerFactory<>(configs);
    }*/

}
