package cn.rwj.study.dpattern._29空对象模式._01菜鸟_人名;

/**
 * @author rwj
 * @date 2023/4/14
 */
public class RealCustomer extends AbstractCustomer{

    public RealCustomer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isNil() {
        return false;
    }

}
