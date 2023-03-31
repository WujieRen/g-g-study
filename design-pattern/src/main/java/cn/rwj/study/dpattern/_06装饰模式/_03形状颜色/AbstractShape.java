package cn.rwj.study.dpattern._06装饰模式._03形状颜色;

/**
 * @author rwj
 * @date 2023/3/31
 */
public abstract class AbstractShape implements Shape{

    private String name;

    public AbstractShape(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public abstract void draw();

}
