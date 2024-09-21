package cn.rwj.study.mybatis;

import cn.rwj.study.mybatis.dao.IUserDao;
import cn.rwj.study.mybatis.io.Resources;
import cn.rwj.study.mybatis.session.SqlSession;
import cn.rwj.study.mybatis.session.SqlSessionFactory;
import cn.rwj.study.mybatis.session.SqlSessionFactoryBuilder;
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
    public void test() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        String res = userDao.queryUserInfoById("10001");
        log.info("测试结果：{}", res);
    }


}
