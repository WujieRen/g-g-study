package cn.rwj.study.ibatis.my.sqlsession;

import cn.rwj.study.ibatis.my.config.XMLConfigBuilder;
import cn.rwj.study.ibatis.my.pojo.Configuration;
import cn.rwj.study.ibatis.my.sqlsession.factory.DefaultSqlSessionFactory;
import cn.rwj.study.ibatis.my.sqlsession.factory.SqlSessionFactory;

import java.io.InputStream;

/**
 * @author rwj
 * @since 2023/9/1
 *
 * XMLConfigBuilder类的parse方法：解析核心配置类，返回Configuration对象
 * 创建SqlSession工厂对象，以便之后创建SqlSession会话
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) throws Exception {
        //1.解析配置文件，封装容器对象：专门解析核心配置文件的解析类
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parse(inputStream);
        //2.创建SqlSessionFactory工厂对象
        return new DefaultSqlSessionFactory(configuration);
    }

}
