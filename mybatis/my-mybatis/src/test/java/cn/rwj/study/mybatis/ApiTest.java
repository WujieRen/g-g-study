package cn.rwj.study.mybatis;

import cn.hutool.json.JSONUtil;
import cn.rwj.study.mybatis.builder.xml.XMLConfigBuilder;
import cn.rwj.study.mybatis.dao.IUserDao;
import cn.rwj.study.mybatis.io.Resources;
import cn.rwj.study.mybatis.po.User;
import cn.rwj.study.mybatis.session.Configuration;
import cn.rwj.study.mybatis.session.SqlSession;
import cn.rwj.study.mybatis.session.SqlSessionFactory;
import cn.rwj.study.mybatis.session.SqlSessionFactoryBuilder;
import cn.rwj.study.mybatis.session.defaults.DefaultSqlSession;
import java.io.IOException;
import java.io.Reader;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author rwj
 * @since 2024/9/21
 */
@Slf4j
public class ApiTest {

    @Test
    public void test_SqlSessionFactory() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        User user = userDao.queryUserInfoById(1L);
        log.info("测试结果：{}", JSONUtil.toJsonStr(user));
    }

    @Test
    public void test_selectOne() throws IOException {
        // 解析 XML
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        Configuration configuration = xmlConfigBuilder.parse();

        // 获取 DefaultSqlSession
        SqlSession sqlSession = new DefaultSqlSession(configuration);

        // 执行查询：默认是一个集合参数
        Object[] req = {1L};
        Object res = sqlSession.selectOne("cn.rwj.study.mybatis.dao.IUserDao.queryUserInfoById", req);
    }

}
