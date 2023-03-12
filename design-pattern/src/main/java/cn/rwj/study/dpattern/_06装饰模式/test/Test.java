package cn.rwj.study.dpattern._06装饰模式.test;

/**
 * @author rwj
 * @date 2023/3/12
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("装饰对象是可以随意去装饰被装饰对象的");
        System.out.println("--------------------- 装饰方式1 ----------------------------");
        Component component = new ConcreteComponent();
        Decorator da = new ConcreteDecoratorA();
        da.setComponent(component);
        da.operation();

        System.out.println("--------------------- 装饰方式2 ----------------------------");
        Decorator db = new ConcreteDecoratorB();
        db.setComponent(component);
        db.operation();


        System.out.println("--------------------- 装饰方式3 ----------------------------");
        Decorator da1 = new ConcreteDecoratorA();
        da1.setComponent(component);
        Decorator db1 = new ConcreteDecoratorB();
        db1.setComponent(da1);
        db1.operation();

    }

}
