package cn.rwj.study.dpattern._02策略模式._04策略模式;

public class CashNormal extends CashSuper {

    //正常收费，原价返回
    public double acceptCash(double price, int num) {
        return price * num;
    }

}
