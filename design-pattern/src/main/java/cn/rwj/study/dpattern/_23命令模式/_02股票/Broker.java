package cn.rwj.study.dpattern._23命令模式._02股票;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rwj
 * @date 2023/4/9
 */
public class Broker {

    private List<Order> orderList = new ArrayList<Order>();

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public void placeOrders() {
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }

}
