package cn.rwj.study.dpattern._09原型模式._02形状;

/**
 * @author rwj
 * @date 2023/3/29
 */
public class Square extends Shape {

    public Square() {
        type = "Square";
    }

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
