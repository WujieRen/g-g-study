package cn.rwj.study.ibatis.my.sqlsession;

import cn.rwj.study.ibatis.my.executor.Executor;
import cn.rwj.study.ibatis.my.pojo.Configuration;
import cn.rwj.study.ibatis.my.pojo.MapperStatement;

import java.lang.reflect.*;
import java.util.List;

/**
 * @author rwj
 * @since 2023/9/1
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }


    /**
     * @param statementId
     * @param param       替换sql语句中的占位符？，可能字符串、对象、Map、集合
     * @param <E>
     * @return
     * @throws Exception
     */
    @Override
    public <E> List<E> selectList(String statementId, Object param) throws Exception {
        // 根据StatementId获取映射配置对象MapperStatement
        MapperStatement mapperStatement = configuration.getMapperStatementMap().get(statementId);
        // 然后将具体的查询操作委派给SimpleExecutor执行器
        // 执行底层jdbc需要：1.数据库配置，2.sql配置信息
        return executor.query(configuration, mapperStatement, param);
    }

    @Override
    public <T> T selectOne(String statementId, Object param) throws Exception {
        // 调用selectList()
        List<Object> selectList = selectList(statementId, param);
        if (selectList.size() == 1) {
            return (T) selectList.get(0);
        } else if (selectList.size() > 1) {
            throw new Exception("返回数据不止一条！！！");
        } else {
            return null;
        }
    }

    @Override
    public void close() {
        executor.close();
    }

}
