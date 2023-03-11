package cn.rwj.study.dpattern._01简单工厂模式._04计算器;

public class Sub extends Operation {
    public double getResult(double numberA, double numberB) {
        return numberA - numberB;
    }
}