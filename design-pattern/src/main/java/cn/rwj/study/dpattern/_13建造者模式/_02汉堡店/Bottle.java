package cn.rwj.study.dpattern._13建造者模式._02汉堡店;

/**
 * @author rwj
 * @date 2023/3/29
 */
public class Bottle implements Packing{
    @Override
    public String pack() {
        return "Bottle -- 装瓶";
    }
}
