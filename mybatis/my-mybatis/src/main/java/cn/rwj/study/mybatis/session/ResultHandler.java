package cn.rwj.study.mybatis.session;

/**
 * 结果处理器
 *
 * @author rwj
 * @since 2024/10/2
 */
public interface ResultHandler {

    /**
     * 处理结果
     */
    void handleResult(ResultContext context);

}
