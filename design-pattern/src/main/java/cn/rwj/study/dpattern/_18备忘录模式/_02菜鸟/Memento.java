package cn.rwj.study.dpattern._18备忘录模式._02菜鸟;

/**
 * @author rwj
 * @date 2023/4/13
 */
public class Memento {

    private String state;


    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

}
