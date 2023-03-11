package cn.rwj.study.dpattern._0动物练习._03重构;

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
