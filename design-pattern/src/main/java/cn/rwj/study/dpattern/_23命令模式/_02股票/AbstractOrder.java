package cn.rwj.study.dpattern._23命令模式._02股票;

/**
 * @author rwj
 * @date 2023/4/9
 */
public abstract class AbstractOrder implements Order{

    protected Stock stock;

    public AbstractOrder(Stock stock) {
        this.stock = stock;
    }

}
