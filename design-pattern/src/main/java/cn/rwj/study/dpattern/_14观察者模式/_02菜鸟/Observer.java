package cn.rwj.study.dpattern._14观察者模式._02菜鸟;

/**
 * @author rwj
 * @date 2023/4/10
 */
public abstract class Observer {

    protected Subject subject;

    public Observer(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    public abstract void update();

}
