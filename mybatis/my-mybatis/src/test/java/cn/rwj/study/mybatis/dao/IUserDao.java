package cn.rwj.study.mybatis.dao;

import cn.rwj.study.mybatis.po.User;

import java.util.List;

/**
 * @author rwj
 * @since 2024/9/21
 */
public interface IUserDao {

    User queryUserInfoById(Long id);

    User queryUserInfo(User req);

    List<User> queryUserInfoList();

    int updateUserInfo(User req);

    void insertUserInfo(User req);

    int deleteUserInfoByUserId(String userId);
    
}
