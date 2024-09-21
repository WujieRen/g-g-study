package cn.rwj.study.mybatis.dao;

/**
 * @author rwj
 * @since 2024/9/21
 */
public interface IUserDao {

    String queryUserName(String uId);

    Integer queryUserAge(String uId);

}
