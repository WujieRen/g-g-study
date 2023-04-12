package cn.rwj.study.dpattern._25中介者模式._03菜鸟_聊天室;

/**
 * @author rwj
 * @date 2023/4/12
 */
public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sendMessage(String message) {
        ChatRoom.showMessage(this, message);
    }

}
