package cn.rwj.study.dpattern._12外观模式._02形状;

/**
 * @author rwj
 * @date 2023/4/5
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle::draw() --> 画个圆形");
    }
}
