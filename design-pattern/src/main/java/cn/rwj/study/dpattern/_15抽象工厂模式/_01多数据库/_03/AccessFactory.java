package cn.rwj.study.dpattern._15抽象工厂模式._01多数据库._03;

//Access工厂
public class AccessFactory implements IFactory {

    public IUser createUser() {
        return new AccessUser();
    }

    public IDepartment createDepartment() {
        return new AccessDepartment();
    }

}


