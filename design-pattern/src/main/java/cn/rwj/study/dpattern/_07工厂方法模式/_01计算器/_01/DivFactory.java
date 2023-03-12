package cn.rwj.study.dpattern._07工厂方法模式._01计算器._01;

//除法工厂
public class DivFactory implements IFactory {

    public Operation createOperation() {
        return new Div();
    }

}
