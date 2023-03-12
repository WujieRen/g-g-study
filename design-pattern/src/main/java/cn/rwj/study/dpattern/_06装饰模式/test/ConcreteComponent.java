package cn.rwj.study.dpattern._06装饰模式.test;

/**
 * @author rwj
 * @date 2023/3/12
 */
public class ConcreteComponent extends Component{
    @Override
    public void operation() {
        System.out.println("具体Component对象的实际操作");
    }
}
