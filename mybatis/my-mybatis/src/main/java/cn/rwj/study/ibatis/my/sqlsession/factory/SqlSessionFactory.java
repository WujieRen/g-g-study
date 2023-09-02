package cn.rwj.study.ibatis.my.sqlsession.factory;

import cn.rwj.study.ibatis.my.sqlsession.SqlSession;

/**
 * @author rwj
 * @since 2023/9/1
 *
 * 为了创建SqlSession会话，调用增删改查方法
 */
public interface SqlSessionFactory {

    // 创建SqlSession对象
    SqlSession openSession();

}
