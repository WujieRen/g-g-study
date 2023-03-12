package cn.rwj.study.dpattern._08工厂方法模式._01计算器._02;

//基础运算工厂
public class FactoryBasic implements IFactory {

    public Operation createOperation(String operType) {
        Operation oper = null;
        switch (operType) {
            case "+":
                oper = new Add();
                break;
            case "-":
                oper = new Sub();
                break;
            case "*":
                oper = new Mul();
                break;
            case "/":
                oper = new Div();
                break;
        }

        return oper;
    }

}


