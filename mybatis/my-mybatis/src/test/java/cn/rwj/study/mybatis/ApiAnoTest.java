package cn.rwj.study.mybatis;

import cn.rwj.study.mybatis.dao.IUserDao;
import cn.rwj.study.mybatis.dao.IUserDaoAno;
import cn.rwj.study.mybatis.io.Resources;
import cn.rwj.study.mybatis.po.User;
import cn.rwj.study.mybatis.session.SqlSession;
import cn.rwj.study.mybatis.session.SqlSessionFactory;
import cn.rwj.study.mybatis.session.SqlSessionFactoryBuilder;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author rwj
 * @since 2024/10/9
 */
@Slf4j
public class ApiAnoTest {

    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void test_insertUserInfo() {
        // 1. 获取映射器对象
        IUserDaoAno userDao = sqlSession.getMapper(IUserDaoAno.class);

        // 2. 测试验证
        User user = new User();
        user.setUserId("10003");
        user.setUserName("rwj");
        user.setUserHead("1_06");

        userDao.insertUserInfo(user);
        log.info("测试结果：{}", "Insert OK");

        // 3. 提交事务
        sqlSession.commit();
    }

    @Test
    public void test_deleteUserInfoByUserId() {
        // 1. 获取映射器对象
        IUserDaoAno userDao = sqlSession.getMapper(IUserDaoAno.class);

        // 2. 测试验证
        int count = userDao.deleteUserInfoByUserId("10003");
        log.info("测试结果：{}", count == 1);

        // 3. 提交事务
        sqlSession.commit();
    }


    @Test
    public void test_updateUserInfo() {
        // 1. 获取映射器对象
        IUserDaoAno userDao = sqlSession.getMapper(IUserDaoAno.class);

        // 2. 测试验证
        int count = userDao.updateUserInfo(new User(1L, "10001", "rwj"));
        log.info("测试结果：{}", count);

        // 3. 提交事务
        sqlSession.commit();
    }

    @Test
    public void test_queryUserInfoById() {
        // 1. 获取映射器对象
        IUserDaoAno userDao = sqlSession.getMapper(IUserDaoAno.class);

        // 2. 测试验证：基本参数
        User user = userDao.queryUserInfoById(1L);
        log.info("测试结果：{}", JSON.toJSONString(user));
    }

    @Test
    public void test_queryUserInfo() {
        // 1. 获取映射器对象
        IUserDaoAno userDao = sqlSession.getMapper(IUserDaoAno.class);

        // 2. 测试验证：对象参数
        User user = userDao.queryUserInfo(new User(1L, "10001"));
        log.info("测试结果：{}", JSON.toJSONString(user));
    }

    @Test
    public void test_queryUserInfoList() {
        // 1. 获取映射器对象
        IUserDaoAno userDao = sqlSession.getMapper(IUserDaoAno.class);

        // 2. 测试验证：对象参数
        List<User> users = userDao.queryUserInfoList();
        log.info("测试结果：{}", JSON.toJSONString(users));
    }

}
