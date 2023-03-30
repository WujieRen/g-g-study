package cn.rwj.study.dpattern._22桥接模式._02形状颜色;

/**
 * @author rwj
 * @date 2023/3/30
 */
public class Main {
    public static void main(String[] args) {

        DrawAPI red = new DrawRed();
        new Circle(red).draw();
        new Rectangle(red).draw();

        DrawAPI green = new DrawGreen();
        new Circle(green).draw();
        new Rectangle(green).draw();

    }
}
