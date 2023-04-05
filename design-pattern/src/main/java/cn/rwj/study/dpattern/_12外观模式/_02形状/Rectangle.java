package cn.rwj.study.dpattern._12外观模式._02形状;

/**
 * @author rwj
 * @date 2023/4/5
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Rectangle::draw() --> 画个长方形");
    }
}
