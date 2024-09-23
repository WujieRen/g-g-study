package cn.rwj.study.mybatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 事务接口
 *
 * @author rwj
 * @since 2024/9/23
 */
public interface Transaction {

    Connection getConnection() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;

}
