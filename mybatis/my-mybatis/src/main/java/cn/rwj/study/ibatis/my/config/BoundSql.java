package cn.rwj.study.ibatis.my.config;

import cn.rwj.study.ibatis.my.utils.ParameterMapping;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author rwj
 * @since 2023/9/1
 */
@Data
@AllArgsConstructor
public class BoundSql {

    // 用?做占位符的sql语句
    private String finalSql;

    // 字段名称的集合
    private List<ParameterMapping> parameterMappingList;

}
