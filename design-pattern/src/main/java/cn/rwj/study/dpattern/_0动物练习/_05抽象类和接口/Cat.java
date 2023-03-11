package cn.rwj.study.dpattern._0动物练习._05抽象类和接口;

public class Cat extends Animal {


	public Cat (){
		super();
	}
	public Cat (String name){
		super(name);
	}

	protected String getShoutSound(){
		return "喵";
	}
}
