package cn.rwj.study.dpattern._15抽象工厂模式._01多数据库._03;

//Sqlserver工厂
public class SqlserverFactory implements IFactory {

    public IUser createUser() {
        return new SqlserverUser();
    }

    public IDepartment createDepartment() {
        return new SqlserverDepartment();
    }

}

