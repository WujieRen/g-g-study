package cn.rwj.study.dpattern._09原型模式._02形状;

/**
 * @author rwj
 * @date 2023/3/29
 */
public class Circle extends Shape {

    public Circle() {
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
