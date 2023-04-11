package cn.rwj.study.dpattern._16状态模式._02菜鸟;

/**
 * @author rwj
 * @date 2023/4/11
 */
public class StatePatternDemo {

    public static void main(String[] args) {
        Context context = new Context();

        State startState = new StartState();
        context.setState(startState);
        System.out.println(context.getState().toString());

        State stopState = new StopState();
        context.setState(stopState);
        System.out.println(context.getState().toString());

    }

}
