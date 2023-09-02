package cn.rwj.study.ibatis.my.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author rwj
 * @since 2023/9/1
 */
@Data
@AllArgsConstructor
public class ParameterMapping {

    // 保存#{}中对于的字段名称
    private String content;

}
