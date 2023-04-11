package cn.rwj.study.dpattern._16状态模式._02菜鸟;

/**
 * @author rwj
 * @date 2023/4/11
 */
public class StopState extends AbstractState {

    @Override
    public void doAction() {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    public String toString() {
        return "Stop State";
    }

}
