package cn.rwj.study.java.qlexpress;

/**
 * @author rwj
 * @since 2023/8/10
 */
public class Express {
    public static void sendCoupon(Integer num) {
        System.out.println("赠送优惠券啦:" + num);
    }

    public static String  sendIntegral(Integer num) {
        System.out.println("赠送积分啦:" + num);
        return "赠送积分--" + num + "--";
    }

    public String sendMsg(String msg) {
        System.out.println("发送短信啦:" + msg);
        return msg+"*****";
    }

    public boolean isNewAcct(Integer userType) {
        if (userType == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isMobile(Integer mobileType) {
        if (mobileType == 1) {
            return true;
        } else {
            return false;
        }
    }

    public String prt(String msg) {
        System.out.println(msg+"```");
        return msg;
    }

}
