package cn.rwj.study.spring.springbasic.idempotence.api.entity;

import lombok.Data;

/**
 * @author rwj
 * @since 2023/9/15
 */
@Data
public class RequestData<T> {

    private Header header;

    private T body;

}
