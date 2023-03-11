package cn.rwj.study.dpattern._0动物练习._05抽象类和接口;

public class MachineCat extends Cat implements IChange {

	public MachineCat (){
		super();
	}
	public MachineCat (String name){
		super(name);
	}

	public String changeThing(String thing){
		return super.shout()+ " 我有万能的口袋，我可变出" + thing;
	}
}
