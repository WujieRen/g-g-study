package cn.rwj.study.spring.kafka.springkafka.config;

import org.apache.kafka.common.config.SaslConfigs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.security.jaas.KafkaJaasLoginModuleInitializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rwj
 * @since 2023/5/4
 */
@Configuration
public class FirstWay {

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
    /*private static void systemPropertisConfig(){
        System.setProperty("java.security.krb5.conf", "D:/test.conf");
        System.setProperty("java.security.auth.login.config", "D:/kafka_server_jaas.conf");
    }

    @Bean
    public KafkaJaasLoginModuleInitializer jaasConfig() throws IOException {
        systemPropertisConfig();
        KafkaJaasLoginModuleInitializer jaasConfig = new KafkaJaasLoginModuleInitializer();
        jaasConfig.setControlFlag(KafkaJaasLoginModuleInitializer.ControlFlag.valueOf("REQUIRED"));
        Map<String, String> options = new HashMap<>();
        options.put("useKeyTab", "true");
        options.put("storeKey", "true");
        options.put("keyTab", "D:\\kafka.keytab");
        options.put("principal", "m1.rwj.cn@EXAMPLE.COM");
        options.put("sasl.login.class", "com.sun.security.auth.module.Krb5LoginModule");
        options.put("sasl.kerberos.service.name", "kafka");
        options.put(SaslConfigs.SASL_MECHANISM, SaslConfigs.GSSAPI_MECHANISM);
        jaasConfig.setOptions(options);
        return jaasConfig;
    }*/

}
