package cn.rwj.study.mybatis.session;

import cn.rwj.study.mybatis.builder.xml.XMLConfigBuilder;
import cn.rwj.study.mybatis.session.defaults.DefaultSqlSessionFactory;
import java.io.Reader;

/**
 * @author rwj
 * @since 2024/9/21
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }

}
