package cn.rwj.study.dpattern._07工厂方法模式._01计算器._02;

public class Div extends Operation {
    public double getResult(double numberA, double numberB) {
        if (numberB == 0) {
            System.out.println("除数不能为0");
            throw new ArithmeticException();
        }
        return numberA / numberB;
    }
}
