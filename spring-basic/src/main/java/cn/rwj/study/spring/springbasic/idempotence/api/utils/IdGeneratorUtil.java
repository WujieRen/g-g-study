package cn.rwj.study.spring.springbasic.idempotence.api.utils;

import java.util.UUID;

/**
 * @author rwj
 * @since 2023/9/15
 */
public final class IdGeneratorUtil {

    private IdGeneratorUtil(){}

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

}
