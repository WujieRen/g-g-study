package cn.rwj.study.mybatis.binding;


import cn.rwj.study.mybatis.dao.IUserDao;
import cn.rwj.study.mybatis.session.SqlSession;
import cn.rwj.study.mybatis.session.defaults.DefaultSqlSessionFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MapperProxyFactoryTest {



    @Test
    public void newInstance() {
        // 1. 注册 Mapper
        MapperRegistry mapperRegistry = new MapperRegistry();
        mapperRegistry.addMappers("cn.rwj.study.mybatis.dao");

        //  2. 从 SqlSession 工厂获取 Session
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(mapperRegistry);
        SqlSession sqlSession = defaultSqlSessionFactory.openSession();

        // 3. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 4. 测试验证
        String res = userDao.queryUserName("10001");
        log.info("测试结果：{}", res);
    }
}