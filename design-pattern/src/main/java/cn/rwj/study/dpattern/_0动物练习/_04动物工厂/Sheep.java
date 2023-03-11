package cn.rwj.study.dpattern._0动物练习._04动物工厂;

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
