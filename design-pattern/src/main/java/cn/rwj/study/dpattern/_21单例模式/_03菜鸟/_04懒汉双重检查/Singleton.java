package cn.rwj.study.dpattern._21单例模式._03菜鸟._04懒汉双重检查;

/**
 * @author rwj
 * @date 2023/3/28
 */
public class Singleton {
    private volatile static Singleton singleton;

    private Singleton() {
    }

    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
