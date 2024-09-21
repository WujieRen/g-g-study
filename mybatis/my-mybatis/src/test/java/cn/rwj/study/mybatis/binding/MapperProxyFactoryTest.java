package cn.rwj.study.mybatis.binding;


import cn.rwj.study.mybatis.dao.IUserDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MapperProxyFactoryTest {



    @Test
    public void newInstance() {
        Map<String, String> sqlSession = new HashMap<>();
        sqlSession.put("cn.rwj.study.mybatis.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
        sqlSession.put("cn.rwj.study.mybatis.dao.IUserDao.queryUserAge", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");

        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);
        IUserDao userDao = factory.newInstance(sqlSession);

        String res = userDao.queryUserName("10001");
        log.info("测试结果：{}", res);
    }
}