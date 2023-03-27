package cn.rwj.study.dpattern._01简单工厂模式._02形状;

/**
 * @author rwj
 * @date 2023/3/27
 */
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
