package cn.rwj.study.dpattern._15抽象工厂模式._01多数据库._06;

//部门类接口
public interface IDepartment {

    void insert(Department department);

    Department getDepartment(int id);
}
