package cn.rwj.study.dpattern._15抽象工厂模式._01多数据库._03;

//部门类接口
public interface IDepartment {

    public void insert(Department department);

    public Department getDepartment(int id);
}

