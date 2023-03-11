package cn.rwj.study.dpattern._0动物练习._05抽象类和接口;

public class Sheep extends Animal {

	public Sheep (){
		super();
	}
	public Sheep (String name){
		super(name);
	}

	protected String getShoutSound(){
		return "咩";
	}
}
