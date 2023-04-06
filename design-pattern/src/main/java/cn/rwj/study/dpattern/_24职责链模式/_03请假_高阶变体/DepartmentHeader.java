package cn.rwj.study.dpattern._24职责链模式._03请假_高阶变体;

/**
 * @author rwj
 * @date 2023/4/6
 */
public class DepartmentHeader implements Ratify {
    @Override
    public Result deal(Chain chain) {
        Request request = chain.request();
        if (request.getDays() > 7) {
            return new Result(false, "你这个完全没必要");
        }
        return new Result(true, "DepartmentHeader：不要着急，把事情处理完再回来！");
    }
}
