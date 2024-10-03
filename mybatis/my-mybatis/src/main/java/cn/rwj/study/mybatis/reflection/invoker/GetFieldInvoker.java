package cn.rwj.study.mybatis.reflection.invoker;

import java.lang.reflect.Field;

/**
 * getter 调用者
 *
 * @author rwj
 * @since 2024/10/2
 */
public class GetFieldInvoker implements Invoker {

    private Field field;

    public GetFieldInvoker(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        return field.get(target);
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
