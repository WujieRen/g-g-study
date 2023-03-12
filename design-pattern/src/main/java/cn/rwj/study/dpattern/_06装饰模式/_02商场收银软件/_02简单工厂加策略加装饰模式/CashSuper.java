package cn.rwj.study.dpattern._06装饰模式._02商场收银软件._02简单工厂加策略加装饰模式;

public class CashSuper implements ISale {

    protected ISale component;

    //装饰对象
    public void decorate(ISale component) {
        this.component = component;
    }

    public double acceptCash(double price, int num) {
        var result = 0d;
        if (this.component != null) {
            //若装饰对象存在，则执行装饰的算法运算
            result = this.component.acceptCash(price, num);
        }
        return result;

    }

}


