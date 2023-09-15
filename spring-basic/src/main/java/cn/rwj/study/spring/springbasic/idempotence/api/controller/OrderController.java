package cn.rwj.study.spring.springbasic.idempotence.api.controller;

import cn.rwj.study.spring.springbasic.idempotence.api.annotation.Idempotent;
import cn.rwj.study.spring.springbasic.idempotence.api.entity.Order;
import cn.rwj.study.spring.springbasic.idempotence.api.entity.RequestData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rwj
 * @since 2023/9/15
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/save-order")
    @Idempotent(name = "requestData", type = RequestData.class, field = "token")
    public String saveOrder(@RequestBody RequestData<Order> requestData) {
        return "success";
    }

}
