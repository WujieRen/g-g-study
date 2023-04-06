package cn.rwj.study.dpattern._24职责链模式._03请假_高阶变体;

/**
 * @author rwj
 * @date 2023/4/6
 */
public interface Ratify {

    // 处理请求
    Result deal(Chain chain);

    /**
     * 接口描述：对request和Result封装，用来转发
     */
    interface Chain {
        // 获取当前request
        Request request();

        // 转发request
        Result proceed(Request request);
    }

}
