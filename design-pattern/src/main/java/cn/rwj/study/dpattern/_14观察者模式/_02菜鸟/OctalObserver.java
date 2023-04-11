package cn.rwj.study.dpattern._14观察者模式._02菜鸟;

/**
 * @author rwj
 * @date 2023/4/10
 */
public class OctalObserver extends Observer {


    public OctalObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Octal String: " + Integer.toOctalString(subject.getState()));
    }
    
}
