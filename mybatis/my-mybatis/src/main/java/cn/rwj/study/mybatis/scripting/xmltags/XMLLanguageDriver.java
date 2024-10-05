package cn.rwj.study.mybatis.scripting.xmltags;

import cn.rwj.study.mybatis.executor.parameter.ParameterHandler;
import cn.rwj.study.mybatis.mappig.BoundSql;
import cn.rwj.study.mybatis.mappig.MappedStatement;
import cn.rwj.study.mybatis.mappig.SqlSource;
import cn.rwj.study.mybatis.scripting.LanguageDriver;
import cn.rwj.study.mybatis.scripting.defaults.DefaultParameterHandler;
import cn.rwj.study.mybatis.session.Configuration;
import org.dom4j.Element;

/**
 * XML语言驱动器
 *
 * @author rwj
 * @since 2024/10/3
 */
public class XMLLanguageDriver implements LanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        // 用XML脚本构建器解析
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }

    @Override
    public ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        return new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
    }

}
