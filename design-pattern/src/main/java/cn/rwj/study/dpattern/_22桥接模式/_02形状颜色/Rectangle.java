package cn.rwj.study.dpattern._22桥接模式._02形状颜色;

/**
 * @author rwj
 * @date 2023/3/30
 */
public class Rectangle extends Shape{

    public Rectangle(DrawAPI drawAPI) {
        super("三角形", drawAPI);
    }

    @Override
    void draw() {
       super.getDrawAPI().draw(this);
    }

}
