package cn.rwj.study.dpattern._22桥接模式._02形状颜色;

/**
 * @author rwj
 * @date 2023/3/30
 */
public class DrawGreen implements DrawAPI {

    @Override
    public void draw(Shape shape) {
        System.out.println("绿色的 --> " + shape.name);
    }

}
