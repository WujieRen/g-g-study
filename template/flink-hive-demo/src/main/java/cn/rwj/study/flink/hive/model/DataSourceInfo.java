package cn.rwj.study.flink.hive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author rwj
 * @since 2024/2/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataSourceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dbName;

    private String tableName;

    private List<FieldInfo> fieldInfos;

}
