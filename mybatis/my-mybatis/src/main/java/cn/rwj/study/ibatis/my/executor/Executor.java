package cn.rwj.study.ibatis.my.executor;

import cn.rwj.study.ibatis.my.pojo.Configuration;
import cn.rwj.study.ibatis.my.pojo.MapperStatement;

import java.util.List;

/**
 * @author rwj
 * @since 2023/9/1
 */
public interface Executor {

    <E> List<E> query(Configuration configuration, MapperStatement mapperStatement, Object param) throws Exception;

    void close();

}
