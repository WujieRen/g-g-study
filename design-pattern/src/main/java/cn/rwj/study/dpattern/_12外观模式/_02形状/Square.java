package cn.rwj.study.dpattern._12外观模式._02形状;

/**
 * @author rwj
 * @date 2023/4/5
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Square::draw() --> 画个圆形");
    }
}
