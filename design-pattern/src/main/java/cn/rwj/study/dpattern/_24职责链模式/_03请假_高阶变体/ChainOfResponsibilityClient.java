package cn.rwj.study.dpattern._24职责链模式._03请假_高阶变体;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rwj
 * @date 2023/4/6
 */
public class ChainOfResponsibilityClient {

    private List<Ratify> ratifies;

    public ChainOfResponsibilityClient() {
        ratifies = new ArrayList<>();
        ratifies.add(new GroupLeader());
        ratifies.add(new Manager());
        ratifies.add(new DepartmentHeader());
    }

    public void addRatify(Ratify ratify) {
        this.ratifies.add(ratify);
    }

    public Result execute(Request request) {
        RealChain realChain = new RealChain(ratifies, request, 0);
        return realChain.proceed(request);
    }

}
