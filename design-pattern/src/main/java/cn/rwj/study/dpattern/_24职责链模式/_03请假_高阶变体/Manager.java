package cn.rwj.study.dpattern._24职责链模式._03请假_高阶变体;

/**
 * @author rwj
 * @date 2023/4/6
 */
public class Manager implements Ratify {
    @Override
    public Result deal(Chain chain) {
        Request request = chain.request();
        if (request.getDays() > 3) {
            // 构建新的Request
            Request newRequest = new Request.Builder().newRequest(request)
                    .setManagerInfo(request.getName() + "每月的KPI考核还不错，可以批准")
                    .build();
            return chain.proceed(newRequest);
        }
        return new Result(true, "Manager：早点把事情办完，项目离不开你");
    }
}
