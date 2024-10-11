package cn.rwj.study.mybatis.executor.keygen;

import cn.rwj.study.mybatis.executor.Executor;
import cn.rwj.study.mybatis.mappig.MappedStatement;

import java.sql.Statement;

/**
 * 不用键值生成器
 *
 * @author rwj
 * @since 2024/10/10
 */
public class NoKeyGenerator implements KeyGenerator {
    @Override
    public void processBefore(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {
        // Do Nothing
    }

    @Override
    public void processAfter(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {
        // Do Nothing
    }
}
