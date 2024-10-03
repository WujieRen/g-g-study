package cn.rwj.study.mybatis.reflection.wrapper;

import cn.rwj.study.mybatis.reflection.MetaObject;

/**
 * 默认对象包装工厂
 *
 * @author rwj
 * @since 2024/10/2
 */
public class DefaultObjectWrapperFactory implements ObjectWrapperFactory {

    @Override
    public boolean hasWrapperFor(Object object) {
        return false;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        throw new RuntimeException("The DefaultObjectWrapperFactory should never be called to provide an ObjectWrapper.");
    }

}
