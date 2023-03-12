package cn.rwj.study.dpattern._06装饰模式._01穿衣._03;

//具体人类
public class Person implements ICharacter {

    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("装扮的" + name);
    }

}



