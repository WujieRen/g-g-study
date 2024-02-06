package cn.rwj.study.dubbo.provider.service;

import cn.rwj.study.dubbo.InfoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @author rwj
 * @since 2024/2/6
 */
@Service
// Service引入的是org.apache.dubbo.config.annotation.Service包
// dubbo提供的Service注解，用于声明对外暴露服务
@DubboService
public class ProviderInfoServiceImpl implements InfoService {
    @Override
    public String getInfo() {
        return "hello，这里是dubbo-provider模块！";
    }
}
