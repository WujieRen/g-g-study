package cn.rwj.study.dpattern._22桥接模式._02形状颜色;

/**
 * @author rwj
 * @date 2023/3/30
 */
public abstract class Shape {

    private DrawAPI drawAPI;
    protected String name;

    protected DrawAPI getDrawAPI() {
        return this.drawAPI;
    }

    public Shape(String name, DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
        this.name = name;
    }

    abstract void draw();

}
