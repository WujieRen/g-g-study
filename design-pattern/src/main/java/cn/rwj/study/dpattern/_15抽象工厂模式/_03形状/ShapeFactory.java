package cn.rwj.study.dpattern._15抽象工厂模式._03形状;


/**
 * @author rwj
 * @date 2023/3/27
 */
public class ShapeFactory {

    //使用 getShape 方法获取形状类型的对象
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }

        IFactory factory;
        switch (shapeType) {
            case "CIRCLE":
            case "RECTANGLE":
            case "SQUARE":
                factory = new BasicFactory();
                break;
            default:
                factory = new AdvancedFactory();

        }

        return factory.getShape(shapeType);
    }
}
