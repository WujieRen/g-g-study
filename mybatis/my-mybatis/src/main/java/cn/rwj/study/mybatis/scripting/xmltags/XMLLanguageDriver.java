package cn.rwj.study.mybatis.scripting.xmltags;

import cn.rwj.study.mybatis.mappig.SqlSource;
import cn.rwj.study.mybatis.scripting.LanguageDriver;
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
}
