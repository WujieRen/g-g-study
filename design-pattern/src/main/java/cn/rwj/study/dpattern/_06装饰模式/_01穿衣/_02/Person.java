package cn.rwj.study.dpattern._06装饰模式._01穿衣._02;

public class Person {

    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("装扮的" + name);
    }

}



