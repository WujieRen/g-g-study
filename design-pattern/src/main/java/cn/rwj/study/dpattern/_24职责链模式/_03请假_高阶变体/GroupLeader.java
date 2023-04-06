package cn.rwj.study.dpattern._24职责链模式._03请假_高阶变体;

/**
 * @author rwj
 * @date 2023/4/6
 */
public class GroupLeader implements Ratify {
    @Override
    public Result deal(Chain chain) {
        Request request = chain.request();
        if (request.getDays() > 1) {
            // 包装新的Request对象
            Request newRequest = new Request.Builder().newRequest(request)
                    .setManagerInfo(request.getName() + "平时表现不错，而且现在项目也不忙")
                    .build();
            return chain.proceed(newRequest);
        }
        return new Result(true, "GroupLeader：早去早回");
    }
}
