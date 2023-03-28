package cn.rwj.study.dpattern._21单例模式._03菜鸟._05静态内部类;

/**
 * @author rwj
 * @date 2023/3/28
 */
public class Singleton {
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    private Singleton() {
    }

    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
