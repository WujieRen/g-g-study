package cn.rwj.study.dpattern._06装饰模式._03形状颜色;

/**
 * @author rwj
 * @date 2023/3/31
 */
public class Rectangle extends AbstractShape{

    public Rectangle(String name) {
        super(name);
    }

    @Override
    public void draw() {
        System.out.print("长方形 -- ");
    }
}
