package cn.rwj.study.flink.hive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author rwj
 * @since 2024/2/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiInfo implements Serializable {

    private static final long serialVersionUID = 3L;

    private String url;

    private String method;

}
