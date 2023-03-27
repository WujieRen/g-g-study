package cn.rwj.study.dpattern._15抽象工厂模式._03形状;

/**
 * @author rwj
 * @date 2023/3/27
 */
public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
