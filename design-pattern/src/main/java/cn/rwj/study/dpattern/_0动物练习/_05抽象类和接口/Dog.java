package cn.rwj.study.dpattern._0动物练习._05抽象类和接口;

public class Dog extends Animal {

	public Dog (){
		super();
	}
	public Dog (String name){
		super(name);
	}

	protected String getShoutSound(){
		return "汪";
	}
}
