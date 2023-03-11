package cn.rwj.study.dpattern._0动物练习._03重构;

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
