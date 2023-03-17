package cn.rwj.study.dpattern._15抽象工厂模式._01多数据库._03;

//部门类
public class Department {

    //部门ID
    private int _id;

    public int getId() {
        return this._id;
    }

    public void setId(int value) {
        this._id = value;
    }

    //部门名称
    private String _name;

    public String getName() {
        return this._name;
    }

    public void setName(String value) {
        this._name = value;
    }

}


