package cn.rwj.study.dpattern._06装饰模式.test;

/**
 * @author rwj
 * @date 2023/3/12
 */
public class ConcreteDecoratorB extends Decorator {

    @Override
    public void operation() {
//        super.operation();
        this.addedBehavior();
        super.operation();
    }

    private void addedBehavior() {
        System.out.println("具体装饰对象B的独有操作");
    }
}
