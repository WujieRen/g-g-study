package cn.rwj.study.dpattern._15抽象工厂模式._01多数据库._03;

//工厂接口
public interface IFactory {

    public IUser createUser();

    public IDepartment createDepartment();
    
}

