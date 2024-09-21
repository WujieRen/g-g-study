package cn.rwj.study.mybatis.session;

/**
 * @author rwj
 * @since 2024/9/21
 */
public interface SqlSessionFactory {

    /**
     * 打开一个 session
     *
     * @return SqlSession
     */
    SqlSession openSession();

}
