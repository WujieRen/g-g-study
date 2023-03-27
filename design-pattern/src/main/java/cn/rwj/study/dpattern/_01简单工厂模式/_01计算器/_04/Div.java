package cn.rwj.study.dpattern._01简单工厂模式._01计算器._04;

public class Div extends Operation {
    public double getResult(double numberA, double numberB) {
        if (numberB == 0) {
            System.out.println("除数不能为0");
            throw new ArithmeticException();
        }
        return numberA / numberB;
    }
}
