package cn.rwj.study.dpattern._09原型模式._02形状;

/**
 * @author rwj
 * @date 2023/3/29
 */
public class Rectangle extends Shape {

    public Rectangle() {
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
