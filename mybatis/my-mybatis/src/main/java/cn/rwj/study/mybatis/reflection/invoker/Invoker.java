package cn.rwj.study.mybatis.reflection.invoker;

/**
 * 调用者
 *
 * @author rwj
 * @since 2024/10/2
 */
public interface Invoker {

    Object invoke(Object target, Object[] args) throws Exception;

    Class<?> getType();

}
