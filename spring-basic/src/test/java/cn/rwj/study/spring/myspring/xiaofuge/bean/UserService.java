package cn.rwj.study.spring.myspring.xiaofuge.bean;

/**
 * @author rwj
 * @since 2023/10/26
 */
public class UserService {

    private String name;

    public UserService() {}

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("").append(name);
        return sb.toString();
    }

}
