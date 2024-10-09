package cn.rwj.study.mybatis.dao;

import cn.rwj.study.mybatis.po.Activity;

/**
 * @author rwj
 * @since 2024/10/9
 */
public interface IActivityDao {

    Activity queryActivityById(Long activityId);

}
