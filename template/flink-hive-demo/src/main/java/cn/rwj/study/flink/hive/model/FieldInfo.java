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
public class FieldInfo implements Serializable, Comparable<FieldInfo> {

    private static final long serialVersionUID = 2L;

    private String fieldName;

    private String fieldType;

    private String comment;

    private Integer sort;

    @Override
    public int compareTo(FieldInfo o) {
        return this.getSort().compareTo(o.getSort());
    }
}
