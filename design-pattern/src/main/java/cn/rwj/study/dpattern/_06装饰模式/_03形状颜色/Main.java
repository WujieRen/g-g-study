package cn.rwj.study.dpattern._06装饰模式._03形状颜色;

/**
 * @author rwj
 * @date 2023/3/31
 */
public class Main {

    public static void main(String[] args) {

        ShapeDecorator redCircle = new RedShapeDecorator(new Circle("小圆"));
        redCircle.draw();

        ShapeDecorator greenCircle = new GreenShapeDecorator(new Circle("胖圆"));
        greenCircle.draw();

        ShapeDecorator greenRectangle = new GreenShapeDecorator(new Rectangle("大长方形"));
        greenRectangle.draw();

    }

}
