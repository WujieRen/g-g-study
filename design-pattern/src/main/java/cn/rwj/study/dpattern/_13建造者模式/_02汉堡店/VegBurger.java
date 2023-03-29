package cn.rwj.study.dpattern._13建造者模式._02汉堡店;

/**
 * @author rwj
 * @date 2023/3/29
 */
public class VegBurger extends Burger{

    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public float price() {
        return 15.0f;
    }

}
