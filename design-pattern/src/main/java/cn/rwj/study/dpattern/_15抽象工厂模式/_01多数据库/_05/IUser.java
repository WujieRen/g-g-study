package cn.rwj.study.dpattern._15抽象工厂模式._01多数据库._05;

//用户类接口
public interface IUser {

    void insert(User user);

    User getUser(int id);
}
