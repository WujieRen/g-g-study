package cn.rwj.study.dpattern._25中介者模式._03菜鸟_聊天室;

import java.util.Date;

/**
 * @author rwj
 * @date 2023/4/12
 */
public class ChatRoom {

    public static void showMessage(User user, String message) {
        System.out.println(new Date().toString() +  " [" + user.getName() + "] : " + message);
    }

}
