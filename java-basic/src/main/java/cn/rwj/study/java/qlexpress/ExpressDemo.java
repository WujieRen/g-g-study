package cn.rwj.study.java.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * @author rwj
 * @since 2023/8/10
 *
 *           if(新用户) {
 *             赠送优惠券;
 *           } else {
 *             赠送积分;
 *           }
 *           if(有手机号){
 *             发送短信;
 *           }
 *
 */
public class ExpressDemo {

    public static void main(String[] args) throws Exception {
        ExpressRunner runner = new ExpressRunner();

        //      如果 新用户 赠送代金券  否则  赠送积分
        //      如果 有手机号  发送短信
        //      定义逻辑
        runner.addOperatorWithAlias("如果", "if", null);
        runner.addOperatorWithAlias("则", "then", null);
        runner.addOperatorWithAlias("否则", "else", null);

        //      定义执行方法
        runner.addFunctionOfClassMethod("赠送优惠券", ExpressDemo.class.getName(), "sendCoupon", new Class[]{Integer.class}, null);
        runner.addFunctionOfClassMethod("赠送积分", ExpressDemo.class.getName(), "sendIntegral", new Class[]{Integer.class}, null);
        runner.addFunctionOfServiceMethod("发送短信", new ExpressDemo(), "sendMsg", new String[]{"String"}, null);

        //      定义逻辑
        runner.addFunctionOfServiceMethod("是否新用户", new ExpressDemo(), "isNewAcct", new Class[]{Integer.class}, null);
        runner.addFunctionOfServiceMethod("是否有手机号", new ExpressDemo(), "isMobile", new Class[]{Integer.class}, null);

        String exp = "如果  (是否新用户(23432)) 则 { 赠送优惠券(199999)} 否则 { 赠送积分(1)} 如果 (是否有手机号(1)) 则 {发送短信(\"欢迎您哦\")}";
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        Object execute = runner.execute(exp, context, null, false, false, null);
        System.out.println(execute);
    }

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

}
