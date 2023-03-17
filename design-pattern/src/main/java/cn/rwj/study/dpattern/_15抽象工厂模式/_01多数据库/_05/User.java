package cn.rwj.study.dpattern._15抽象工厂模式._01多数据库._05;

//用户类
public class User {

    //用户ID
    private int _id;

    public int getId() {
        return this._id;
    }

    public void setId(int value) {
        this._id = value;
    }

    //用户姓名
    private String _name;

    public String getName() {
        return this._name;
    }

    public void setName(String value) {
        this._name = value;
    }

}
