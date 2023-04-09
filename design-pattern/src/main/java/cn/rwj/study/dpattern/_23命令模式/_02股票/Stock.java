package cn.rwj.study.dpattern._23命令模式._02股票;

/**
 * @author rwj
 * @date 2023/4/9
 */
public class Stock {
    private String name = "ABC";
    private int quantity = 10;

    public void buy(){
        System.out.println("Stock [ Name: "+name+", Quantity: " + quantity +" ] bought");
    }
    public void sell(){
        System.out.println("Stock [ Name: "+name+", Quantity: " + quantity +" ] sold");
    }
}
