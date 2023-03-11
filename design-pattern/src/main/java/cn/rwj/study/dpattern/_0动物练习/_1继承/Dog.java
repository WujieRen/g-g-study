package cn.rwj.study.dpattern._0动物练习._1继承;

public class Dog extends Animal {

	public Dog (){
		super();
	}
	
	public Dog (String name){
		super(name);
	}

	public String shout(){
		String result="";
		for(int i=0;i<this.shoutNum;i++){
			result+= "汪 ";
		}
		return " 我的名字叫"+ name + " " + result;
	}
}
