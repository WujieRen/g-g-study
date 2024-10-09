package cn.rwj.study.mybatis.session;

/**
 * 结果上下文
 *
 * @author rwj
 * @since 2024/10/5
 */
public interface ResultContext {

    /**
     * 获取结果
     */
    Object getResultObject();

    /**
     * 获取记录数
     */
    int getResultCount();

}
