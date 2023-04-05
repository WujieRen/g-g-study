package cn.rwj.study.dpattern._26享元模式._02形状;

import java.util.HashMap;

/**
 * @author rwj
 * @date 2023/4/5
 */
public class ShapeFactory {
    private static final HashMap<String, Shape> circleMap = new HashMap<>();

    public static Shape getCircle(String color) {
        Circle circle = (Circle) circleMap.get(color);

        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color : " + color);
        }
        return circle;
    }
}
