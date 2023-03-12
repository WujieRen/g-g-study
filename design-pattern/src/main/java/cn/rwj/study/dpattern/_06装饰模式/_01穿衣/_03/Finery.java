package cn.rwj.study.dpattern._06装饰模式._01穿衣._03;

//服饰类
public class Finery implements ICharacter {

    protected ICharacter component;

    public void decorate(ICharacter component) {
        this.component = component;
    }

    public void show() {
        if (this.component != null) {
            this.component.show();
        }
    }

}



