package cn.rwj.study.mybatis.mappig;

import cn.rwj.study.mybatis.session.Configuration;
import cn.rwj.study.mybatis.type.JdbcType;
import cn.rwj.study.mybatis.type.TypeHandler;

/**
 * 结果映射
 *
 * @author rwj
 * @since 2024/10/5
 */
public class ResultMapping {

    private Configuration configuration;
    private String property;
    private String column;
    private Class<?> javaType;
    private JdbcType jdbcType;
    private TypeHandler<?> typeHandler;

    ResultMapping() {
    }

    public static class Builder {
        private ResultMapping resultMapping = new ResultMapping();


    }

}
