package cn.rwj.study.dpattern._23命令模式._02股票;

/**
 * @author rwj
 * @date 2023/4/9
 */
public class CommandPatternDemo {
    public static void main(String[] args) {
        Stock abcStock = new Stock();

        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);

        Broker broker = new Broker();
        broker.addOrder(buyStockOrder);
        broker.addOrder(sellStockOrder);

        broker.placeOrders();
    }
}
