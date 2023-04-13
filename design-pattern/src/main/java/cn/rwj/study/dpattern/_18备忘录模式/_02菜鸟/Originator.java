package cn.rwj.study.dpattern._18备忘录模式._02菜鸟;

/**
 * @author rwj
 * @date 2023/4/13
 */
public class Originator {

    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    public void getStateFromMemento(Memento Memento) {
        state = Memento.getState();
    }

}
