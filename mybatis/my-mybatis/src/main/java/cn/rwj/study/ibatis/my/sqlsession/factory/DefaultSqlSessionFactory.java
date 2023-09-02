package cn.rwj.study.ibatis.my.sqlsession.factory;

import cn.rwj.study.ibatis.my.executor.Executor;
import cn.rwj.study.ibatis.my.executor.SimpleExecutor;
import cn.rwj.study.ibatis.my.pojo.Configuration;
import cn.rwj.study.ibatis.my.sqlsession.DefaultSqlSession;
import cn.rwj.study.ibatis.my.sqlsession.SqlSession;

/**
 * @author rwj
 * @since 2023/9/1
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        // 1.创建执行器对象-具体的包装jdbc的sql操作，关闭连接等
        Executor simpleExecutor = new SimpleExecutor();
        // 2.创建sqlSession对象-判断执行增删改查哪些操作等
        return new DefaultSqlSession(configuration, simpleExecutor);
    }
}
