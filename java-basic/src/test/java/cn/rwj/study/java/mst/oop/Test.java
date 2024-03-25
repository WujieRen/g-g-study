package cn.rwj.study.java.mst.oop;

/**
 * @author rwj
 * @since 2023/11/19
 */
public class Test {

    public static void main(String[] args) {
        A a = new A();
        a.scan();

        B b = new B();
        b.scan();  //我的输出结果是什么？

        A b2 = new B();
        b2.scan();

        B b3 = (B) new A();
        b3.scan();
    }
}


class A {
    public void scan(){
        doScan();
    }
    protected void doScan(){
        System.out.println("A.doScan");
    }
}
class B extends A {
    @Override
    protected void doScan() {
        System.out.println("B.doScan");
    }
}