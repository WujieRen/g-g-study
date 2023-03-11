package cn.rwj.study.dpattern._0动物练习._03重构;

public class Cattle extends Animal {

	public Cattle (){
		super();
	}
	public Cattle (String name){
		super(name);
	}

	protected String getShoutSound(){
		return "哞";
	}
}
