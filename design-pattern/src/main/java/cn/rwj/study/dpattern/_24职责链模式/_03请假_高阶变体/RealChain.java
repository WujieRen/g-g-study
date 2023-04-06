package cn.rwj.study.dpattern._24职责链模式._03请假_高阶变体;

import java.util.List;

/**
 * @author rwj
 * @date 2023/4/6
 */
public class RealChain implements Ratify.Chain {

    public Request request;
    public List<Ratify> ratifyList;
    public int index;

    public RealChain(List<Ratify> ratifyList, Request request, int index) {
        this.ratifyList = ratifyList;
        this.request = request;
        this.index = index;
    }

    @Override
    public Request request() {
        return request;
    }

    /**
     * 这里是这种变体最精妙的地方，通过对 index++ 实现对 链节点 的遍历
     *
     * @param request
     * @return
     */
    @Override
    public Result proceed(Request request) {
        Result proceed = null;
        if (ratifyList.size() > index) {
            Ratify.Chain chain = new RealChain(ratifyList, request, index + 1);
            Ratify ratify = ratifyList.get(index);
            proceed = ratify.deal(chain);
        }
        return proceed;
    }

}
