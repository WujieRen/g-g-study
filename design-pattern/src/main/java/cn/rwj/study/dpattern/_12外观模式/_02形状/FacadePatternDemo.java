package cn.rwj.study.dpattern._12外观模式._02形状;

/**
 * @author rwj
 * @date 2023/4/5
 */
public class FacadePatternDemo {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}
