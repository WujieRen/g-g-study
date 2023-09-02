package cn.rwj.study.ibatis.my.sqlsession;

import java.util.List;

/**
 * @author rwj
 * @since 2023/9/1
 */
public interface SqlSession {

    // 查询多个结果
    <E> List<E> selectList(String statementId, Object param) throws Exception;

    // 查询单个结果
    <T> T selectOne(String statementId, Object param) throws Exception;

    // 清理资源
    void close();

}
