package cn.rwj.study.dpattern._25中介者模式._03菜鸟_聊天室;

/**
 * @author rwj
 * @date 2023/4/12
 */
public class MediatorPatternDemo {

    public static void main(String[] args) {
        User robert = new User("Robert");
        User john = new User("John");

        robert.sendMessage("Hi! John!");
        john.sendMessage("Hello! Robert!");
    }

}
