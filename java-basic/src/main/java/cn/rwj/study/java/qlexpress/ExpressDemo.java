package cn.rwj.study.java.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * @author rwj
 * @since 2023/8/10
 * <p>
 * if(新用户) {
 * 赠送优惠券;
 * } else {
 * 赠送积分;
 * }
 * if(有手机号){
 * 发送短信;
 * }
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
        runner.addFunctionOfClassMethod("赠送优惠券", Express.class.getName(), "sendCoupon", new Class[]{Integer.class}, null);
        runner.addFunctionOfClassMethod("赠送积分", Express.class.getName(), "sendIntegral", new Class[]{Integer.class}, null);
        runner.addFunctionOfServiceMethod("发送短信", new Express(), "sendMsg", new String[]{"String"}, null);

        //      定义逻辑
        runner.addFunctionOfServiceMethod("是否新用户", new Express(), "isNewAcct", new Class[]{Integer.class}, null);
        runner.addFunctionOfServiceMethod("是否有手机号", new Express(), "isMobile", new Class[]{Integer.class}, null);

//        String exp = "如果  (是否新用户(23432)) 则 { 赠送优惠券(199999)} 否则 { 赠送积分(1)} 如果 (是否有手机号(1)) 则 {发送短信(\"欢迎您哦\")}";
        String exp = "如果  (是否新用户(23432)) 则 { 赠送优惠券(199999)} 否则 { 赠送积分(1)} 如果 (是否有手机号(1)) 则 {发送短信(赠送积分(1))}";
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        Object execute = runner.execute(exp, context, null, false, false, null);
        System.out.println(execute);

//        runner.addFunctionOfServiceMethod("打印", new Express(), "prt", new Class[]{String.class}, null);
        runner.addFunctionOfClassMethod("打印", Express.class.getName(), "prt", new Class[]{String.class}, null);
//        exp = "打印(打印(\"打印(打印(\"欢迎您！\"))\"))";  //语法错误
        exp = "打印(打印(打印(打印(\"欢迎您！\"))))";
        runner.execute(exp, context, null, false, false, null);

    }

}



