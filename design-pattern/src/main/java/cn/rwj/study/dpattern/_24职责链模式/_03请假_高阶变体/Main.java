package cn.rwj.study.dpattern._24职责链模式._03请假_高阶变体;

/**
 * @author rwj
 * @date 2023/4/6
 */
public class Main {

    public static void main(String[] args) {
        Request request = new Request.Builder().setName("张三").setDays(1).setReason("事假").build();
        ChainOfResponsibilityClient client = new ChainOfResponsibilityClient();
        Result result = client.execute(request);
        System.out.println(result);
    }

}
