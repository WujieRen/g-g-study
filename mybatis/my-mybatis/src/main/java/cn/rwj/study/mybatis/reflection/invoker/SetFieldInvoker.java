package cn.rwj.study.mybatis.reflection.invoker;

import java.lang.reflect.Field;

/**
 * setter 调用者
 *
 * @author rwj
 * @since 2024/10/2
 */
public class SetFieldInvoker implements Invoker {

    private Field field;

    public SetFieldInvoker(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        field.set(target, args[0]);
        return null;
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
