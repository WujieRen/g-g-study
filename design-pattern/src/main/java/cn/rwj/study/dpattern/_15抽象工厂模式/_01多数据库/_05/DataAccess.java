package cn.rwj.study.dpattern._15抽象工厂模式._01多数据库._05;

import java.lang.reflect.InvocationTargetException;

public class DataAccess {
    private static String assemblyName = "code.chapter15.abstractfactory5.";
    private static String db = "Sqlserver";//数据库名称，可替换成Access

    //创建用户对象工厂
    public static IUser createUser() {
        return (IUser) getInstance(assemblyName + db + "User");
    }

    //创建部门对象工厂
    public static IDepartment createDepartment() {
        return (IDepartment) getInstance(assemblyName + db + "Department");
    }

    private static Object getInstance(String className) {
        Object result = null;
        try {
            result = Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException
                 | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
