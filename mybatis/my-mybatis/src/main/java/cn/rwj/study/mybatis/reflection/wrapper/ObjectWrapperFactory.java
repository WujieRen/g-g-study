package cn.rwj.study.mybatis.reflection.wrapper;

import cn.rwj.study.mybatis.reflection.MetaObject;

/**
 * 对象包装工厂
 *
 * @author rwj
 * @since 2024/10/2
 */
public interface ObjectWrapperFactory {

    /**
     * 判断有没有包装器
     */
    boolean hasWrapperFor(Object object);

    /**
     * 得到包装器
     */
    ObjectWrapper getWrapperFor(MetaObject metaObject, Object object);

}
