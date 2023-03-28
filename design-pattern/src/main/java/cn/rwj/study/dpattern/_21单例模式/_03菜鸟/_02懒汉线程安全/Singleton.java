package cn.rwj.study.dpattern._21单例模式._03菜鸟._02懒汉线程安全;

/**
 * @author rwj
 * @date 2023/3/28
 */
public class Singleton {
    private static Singleton instance;
    private Singleton (){}
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
