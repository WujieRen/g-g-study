//TODO: 如果把该类注释掉，会导致找不到 UserMapper、UserServiceImpl 等bean。
package cn.rwj.study.spring.postgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication(scanBasePackages = "cn.rwj.study.spring")
public class PostgresApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostgresApplication.class, args);
	}

}
