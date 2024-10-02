package cn.rwj.study.mybatis;

import cn.hutool.json.JSONUtil;
import cn.rwj.study.mybatis.builder.xml.XMLConfigBuilder;
import cn.rwj.study.mybatis.dao.IUserDao;
import cn.rwj.study.mybatis.datasource.pooled.PooledDataSource;
import cn.rwj.study.mybatis.io.Resources;
import cn.rwj.study.mybatis.po.User;
import cn.rwj.study.mybatis.session.Configuration;
import cn.rwj.study.mybatis.session.SqlSession;
import cn.rwj.study.mybatis.session.SqlSessionFactory;
import cn.rwj.study.mybatis.session.SqlSessionFactoryBuilder;
import cn.rwj.study.mybatis.session.defaults.DefaultSqlSession;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

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
    public void test_pooled() throws SQLException, InterruptedException {
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver("com.mysql.cj.jdbc.Driver");
        pooledDataSource.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=false");
        pooledDataSource.setUsername("root");
        pooledDataSource.setPassword("123qwe");
        // 持续获得链接
        while (true){
            Connection connection = pooledDataSource.getConnection();
            System.out.println(connection);
            Thread.sleep(1000);
            connection.close();
        }
    }

    @Test
    public void testPoolOrUnpool() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        for (int i = 0; i < 50; i++) {
            User user = userDao.queryUserInfoById(1L);
            log.info("测试结果：{}", JSONUtil.toJsonStr(user));
        }
    }



}
