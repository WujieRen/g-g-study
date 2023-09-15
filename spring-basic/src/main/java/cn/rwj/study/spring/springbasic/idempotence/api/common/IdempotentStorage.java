package cn.rwj.study.spring.springbasic.idempotence.api.common;

/**
 * @author rwj
 * @since 2023/9/15
 */
public interface IdempotentStorage {

    void save(String idempotentId);

    boolean delete(String idempotentId);

}
