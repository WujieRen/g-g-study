package cn.rwj.study.dpattern._0动物练习._05抽象类和接口;

public class Monkey extends Animal {

	public Monkey (){
		super();
	}
	public Monkey (String name){
		super(name);
	}

	protected String getShoutSound(){
		return "吱";
	}
}
