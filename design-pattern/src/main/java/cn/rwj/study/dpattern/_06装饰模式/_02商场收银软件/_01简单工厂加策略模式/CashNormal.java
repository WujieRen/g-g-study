package cn.rwj.study.dpattern._06装饰模式._02商场收银软件._01简单工厂加策略模式;

public class CashNormal extends CashSuper {

    //正常收费，原价返回
    public double acceptCash(double price, int num) {
        return price * num;
    }

}
