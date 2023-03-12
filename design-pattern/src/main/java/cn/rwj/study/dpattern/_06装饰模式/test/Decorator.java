package cn.rwj.study.dpattern._06装饰模式.test;

/**
 * @author rwj
 * @date 2023/3/12
 */
public abstract class Decorator extends Component{

    private Component component;

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        if(this.component != null) {
            this.component.operation();
        }
    }

}
