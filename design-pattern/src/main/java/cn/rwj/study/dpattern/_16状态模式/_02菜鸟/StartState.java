package cn.rwj.study.dpattern._16状态模式._02菜鸟;

/**
 * @author rwj
 * @date 2023/4/11
 */
public class StartState extends AbstractState {

    @Override
    public void doAction() {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    public String toString() {
        return "Start State";
    }
}
