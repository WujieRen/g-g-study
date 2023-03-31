package cn.rwj.study.dpattern._06装饰模式._03形状颜色;

/**
 * @author rwj
 * @date 2023/3/31
 */
public class RedShapeDecorator extends ShapeDecorator{

    public RedShapeDecorator(AbstractShape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        System.out.print("经过红色装饰的 -- ");
        super.draw();
        System.out.println(shape.getName());
    }
}
