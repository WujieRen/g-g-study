package cn.rwj.study.dpattern._23命令模式._02股票;

/**
 * @author rwj
 * @date 2023/4/9
 */
public class SellStock extends AbstractOrder{

    public SellStock(Stock stock) {
        super(stock);
    }

    @Override
    public void execute() {
        stock.sell();
    }
}
