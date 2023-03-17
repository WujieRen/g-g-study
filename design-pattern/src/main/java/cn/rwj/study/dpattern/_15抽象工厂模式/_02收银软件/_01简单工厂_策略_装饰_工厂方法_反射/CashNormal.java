package cn.rwj.study.dpattern._15抽象工厂模式._02收银软件._01简单工厂_策略_装饰_工厂方法_反射;

public class CashNormal implements ISale {
    //正常收费，原价返回
    public double acceptCash(double price, int num) {
        return price * num;
    }
}
