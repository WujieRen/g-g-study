package cn.rwj.study.dpattern._22桥接模式._02形状颜色;

/**
 * @author rwj
 * @date 2023/3/30
 */
public class Circle extends Shape{

    public Circle(DrawAPI drawAPI) {
        super("圆圈~~~", drawAPI);
    }

    @Override
    void draw() {
        super.getDrawAPI().draw(this);
    }

}
