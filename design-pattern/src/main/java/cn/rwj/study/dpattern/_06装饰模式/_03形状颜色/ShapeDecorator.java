package cn.rwj.study.dpattern._06装饰模式._03形状颜色;

/**
 * @author rwj
 * @date 2023/3/31
 */
public abstract class ShapeDecorator implements Shape{

    protected AbstractShape shape;

    public ShapeDecorator(AbstractShape shape) {
        this.shape = shape;
    }

    @Override
    public void draw() {
        shape.draw();       // 无论哪个子类实现了该 Decorator，都要调用 被装饰者 的 drw()， 那么就可以把它抽象到父类中去
    }
}
