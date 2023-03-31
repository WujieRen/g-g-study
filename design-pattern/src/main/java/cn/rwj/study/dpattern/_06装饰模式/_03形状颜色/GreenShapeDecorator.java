package cn.rwj.study.dpattern._06装饰模式._03形状颜色;

/**
 * @author rwj
 * @date 2023/3/31
 */
public class GreenShapeDecorator extends ShapeDecorator{

    public GreenShapeDecorator(AbstractShape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        System.out.print("经过绿色装饰的 -- ");
        super.draw();
        System.out.println(shape.getName());
    }
}
