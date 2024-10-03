package cn.rwj.study.mybatis.scripting;

import cn.rwj.study.mybatis.mappig.SqlSource;
import cn.rwj.study.mybatis.session.Configuration;
import org.dom4j.Element;

/**
 * 脚本语言驱动
 *
 * @author rwj
 * @since 2024/10/3
 */
public interface LanguageDriver {

    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);

}
