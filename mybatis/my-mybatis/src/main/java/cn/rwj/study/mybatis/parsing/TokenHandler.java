package cn.rwj.study.mybatis.parsing;

/**
 * 记号处理器
 *
 * @author rwj
 * @since 2024/10/3
 */
public interface TokenHandler {

    String handleToken(String content);

}
