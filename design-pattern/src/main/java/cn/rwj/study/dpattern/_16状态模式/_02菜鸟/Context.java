package cn.rwj.study.dpattern._16状态模式._02菜鸟;

/**
 * @author rwj
 * @date 2023/4/11
 */
public class Context {

    private State state;

    public Context(){
        state = null;
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        return state;
    }

}
