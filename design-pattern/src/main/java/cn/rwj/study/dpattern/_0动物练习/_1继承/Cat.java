package cn.rwj.study.dpattern._0动物练习._1继承;

public class Cat extends Animal {

	public Cat (){
		super();
	}

	public Cat (String name){
		super(name);
	}
	
	public String shout(){
		String result="";
		for(int i=0;i<this.shoutNum;i++){
			result+= "喵 ";
		}
		return " 我的名字叫"+ name + " " + result;
	}
}
