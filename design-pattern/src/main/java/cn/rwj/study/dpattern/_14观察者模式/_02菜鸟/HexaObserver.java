package cn.rwj.study.dpattern._14观察者模式._02菜鸟;

/**
 * @author rwj
 * @date 2023/4/10
 */
public class HexaObserver extends Observer {

    public HexaObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Hex String: " + Integer.toHexString(subject.getState()).toUpperCase());
    }

}
