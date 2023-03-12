package cn.rwj.study.dpattern._06装饰模式._02商场收银软件._02简单工厂加策略加装饰模式;

public class CashNormal implements ISale {
    //正常收费，原价返回
    public double acceptCash(double price, int num) {
        return price * num;
    }
}
