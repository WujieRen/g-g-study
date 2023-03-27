package cn.rwj.study.dpattern._01简单工厂模式._01计算器._04;

public class Sub extends Operation {
    public double getResult(double numberA, double numberB) {
        return numberA - numberB;
    }
}