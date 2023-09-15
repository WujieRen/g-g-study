package cn.rwj.study.spring.springbasic.idempotence.api.controller;

import cn.rwj.study.spring.springbasic.idempotence.api.common.RedisIdempotentStorage;
import cn.rwj.study.spring.springbasic.idempotence.api.utils.IdGeneratorUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author rwj
 * @since 2023/9/15
 */
@RestController
@RequestMapping("/idGenerator")
public class IdGeneratorController {

    @Resource
    private RedisIdempotentStorage redisIdempotentStorage;

    @GetMapping("/getIdGeneratorToken")
    public String getIdGeneratorToken() {
        String generateId = IdGeneratorUtil.generateId();
        redisIdempotentStorage.save(generateId);
        return generateId;
    }

}
