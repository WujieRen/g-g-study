package cn.rwj.study.dpattern._02策略模式._05策略模式和简单工厂模式结合;

public class CashNormal extends CashSuper {

    //正常收费，原价返回
    public double acceptCash(double price, int num) {
        return price * num;
    }

}
