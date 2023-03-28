package cn.rwj.study.dpattern._21单例模式._03菜鸟._03饿汉线程安全;

/**
 * @author rwj
 * @date 2023/3/28
 */
public class Singleton {
    private static Singleton instance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }
}
