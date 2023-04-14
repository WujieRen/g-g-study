package cn.rwj.study.dpattern._29空对象模式._01菜鸟_人名;

/**
 * @author rwj
 * @date 2023/4/14
 */
public class NullCustomer extends AbstractCustomer{

    @Override
    public String getName() {
        return "Not Available in Customer Database";
    }

    @Override
    public boolean isNil() {
        return true;
    }

}
