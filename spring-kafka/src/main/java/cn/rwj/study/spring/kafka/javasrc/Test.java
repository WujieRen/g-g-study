package cn.rwj.study.spring.kafka.javasrc;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author rwj
 * @since 2023/5/4
 */
@Slf4j
public class Test {

    public static KafkaConsumer<String, String> createConsumer() {
        systemPropertisConfig();
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "10.114.12.46:6667");
        properties.put("group.id", "index-engine");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.put("security.protocol", "SASL_PLAINTEXT");
//        properties.put("sasl.mechanism", "GSSAPI");
//        properties.put("sasl.kerberos.service.name", "kafka");
//        properties.put("sasl.keystore.password", "kafka");
//        properties.put("sssl.keystore.location", "D:\\kafka.keytab");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(Arrays.asList("test"));
        return kafkaConsumer;
    }
    /***
     * 生成生产者并返回
     * @return
     */
    public static KafkaProducer<String, String> createProducer() {
        log.info("Create kafka producer");
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "m1.rwj.cn:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        System.setProperty("java.security.auth.login.config", "conf/kafka_client_jaas.conf");
        properties.put("security.protocol", "SASL_PLAINTEXT");
        properties.put("sasl.mechanism", "PLAIN");
        return new KafkaProducer<String, String>(properties);
    }


    private static void systemPropertisConfig() {
//        System.setProperty("java.security.krb5.conf", "D:/test.conf");
//        System.setProperty("java.security.auth.login.config", "D:/kafka_server_jaas.conf");
    }

    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = createConsumer();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s" + "\n", record.offset(), record.key(), record.value());
            }
        }
    }

}
