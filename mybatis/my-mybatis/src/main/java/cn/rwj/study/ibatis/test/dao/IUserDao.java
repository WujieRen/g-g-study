package cn.rwj.study.ibatis.test.dao;

import cn.rwj.study.ibatis.test.model.User;

import java.util.List;

/**
 * @author rwj
 * @since 2023/9/1
 */
public interface IUserDao {

    /**
     * 查询所有
     */
    List<User> selectList() throws Exception;

    /**
     * 根据多条件查询
     */
    User selectOne(User user) throws Exception;

}
