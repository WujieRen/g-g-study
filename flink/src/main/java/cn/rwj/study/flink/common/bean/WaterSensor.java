package cn.rwj.study.flink.common.bean;

import lombok.*;


/**
 * @author rwj
 * @since 2023/7/26
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class WaterSensor {

    public String id;
    public Long ts;
    public Integer vc;

}
