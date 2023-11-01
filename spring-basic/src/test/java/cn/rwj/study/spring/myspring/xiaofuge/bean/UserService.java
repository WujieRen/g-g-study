package cn.rwj.study.spring.myspring.xiaofuge.bean;

/**
 * @author rwj
 * @since 2023/10/26
 */
public class UserService {

    private String uId;

    private UserDao userDao;

    public String queryUserInfo() {
        String s = userDao.queryUserName(uId);
        System.out.println("查询用户信息：" + s);
        return s;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
