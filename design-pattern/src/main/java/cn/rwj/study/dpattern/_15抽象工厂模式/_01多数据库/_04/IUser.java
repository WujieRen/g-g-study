package cn.rwj.study.dpattern._15抽象工厂模式._01多数据库._04;

//用户类接口
public interface IUser {

    public void insert(User user);

    public User getUser(int id);
}
