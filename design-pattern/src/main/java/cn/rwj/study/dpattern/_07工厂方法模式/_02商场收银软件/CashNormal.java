package cn.rwj.study.dpattern._07工厂方法模式._02商场收银软件;

public class CashNormal implements ISale {
    //正常收费，原价返回
    public double acceptCash(double price, int num) {
        return price * num;
    }
}
