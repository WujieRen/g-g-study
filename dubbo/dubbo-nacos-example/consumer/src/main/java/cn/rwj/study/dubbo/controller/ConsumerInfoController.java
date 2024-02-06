package cn.rwj.study.dubbo.controller;

import cn.rwj.study.dubbo.InfoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rwj
 * @since 2024/2/6
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerInfoController {

    //dumbo提供的Reference注解，用于调用远程服务
    @DubboReference(check = false)
    private InfoService infoService;

    @GetMapping
    public String getInfo() {
        return infoService.getInfo();
    }

}
