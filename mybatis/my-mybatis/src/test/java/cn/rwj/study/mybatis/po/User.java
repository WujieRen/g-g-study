package cn.rwj.study.mybatis.po;


import java.util.Date;

/**
 * @author rwj
 * @since 2024/9/21
 */
public class User {

    private Long id;
    // 用户ID
    private String userId;
    // 用户名称
    private String userName;
    // 头像
    private String userHead;
    // 创建时间
    private java.util.Date createTime;
    // 更新时间
    private java.util.Date updateTime;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
