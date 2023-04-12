package cn.rwj.study.dpattern._27解释器模式._02菜鸟;

/**
 * @author rwj
 * @date 2023/4/12
 */
public class TerminalExpression implements Expression{

    private String data;

    public TerminalExpression(String data){
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        if(context.contains(data)){
            return true;
        }
        return false;
    }

}
