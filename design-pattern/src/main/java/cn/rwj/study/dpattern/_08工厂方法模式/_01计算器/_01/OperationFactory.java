package cn.rwj.study.dpattern._08工厂方法模式._01计算器._01;

public class OperationFactory {

    public static Operation createOperate(String operate) {
        Operation oper = null;
        IFactory factory = null;
        switch (operate) {
            case "+":
                factory = new AddFactory();
                break;
            case "-":
                factory = new SubFactory();
                break;
            case "*":
                factory = new MulFactory();
                break;
            case "/":
                factory = new DivFactory();
                break;
        }
        oper = factory.createOperation();

        return oper;
    }

}
