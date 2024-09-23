package cn.rwj.study.mybatis.dao;

import cn.rwj.study.mybatis.po.User;

/**
 * @author rwj
 * @since 2024/9/21
 */
public interface IUserDao {

    User queryUserInfoById(Long uId);

}
