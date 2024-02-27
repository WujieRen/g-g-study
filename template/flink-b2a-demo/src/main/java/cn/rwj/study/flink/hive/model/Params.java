package cn.rwj.study.flink.hive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rwj
 * @since 2024/2/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Params {

    private ApiInfo apiInfo;

    private SourceInfo sourceInfo;

    private SinkInfo sinkInfo;

}




