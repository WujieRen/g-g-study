package cn.rwj.study.dpattern._07工厂方法模式._01计算器._01;

//加法工厂
public class AddFactory implements IFactory {

    public Operation createOperation() {
        return new Add();
    }

}
