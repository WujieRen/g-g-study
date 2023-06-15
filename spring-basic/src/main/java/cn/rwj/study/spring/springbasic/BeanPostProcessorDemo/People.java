package cn.rwj.study.spring.springbasic.BeanPostProcessorDemo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author rwj
 * @since 2023/6/15
 */
@Data
@Component
public class People {

    @DecryptValue(value = "${test.demo}")
    private String name = "张三";

    public void say(){
        System.out.println("我是"+name);
    }

}
