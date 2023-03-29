package cn.rwj.study.dpattern._13建造者模式._02汉堡店;

/**
 * @author rwj
 * @date 2023/3/29
 */
public class Pepsi extends ColdDrink{

    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public float price() {
        return 5.0f;
    }

}
