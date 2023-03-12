package cn.rwj.study.dpattern._06装饰模式.test;

/**
 * @author rwj
 * @date 2023/3/12
 */
public class ConcreteDecoratorA extends Decorator{

    private String addState;

    @Override
    public void operation() {
//        super.operation();

        this.addState = "具体对象A的独有操作";
        System.out.println(this.addState);

        super.operation();
    }
}
