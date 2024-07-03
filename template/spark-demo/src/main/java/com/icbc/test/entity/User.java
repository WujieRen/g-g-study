package com.icbc.test.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author rwj
 * @since 2024/7/3
 */
@Data
@Builder
public class User {

    private String name;
    private Integer age;
    private String sex;

}