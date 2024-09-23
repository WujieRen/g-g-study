package cn.rwj.study.mybatis.transaction;

import cn.rwj.study.mybatis.session.TransactionIsolationLevel;
import java.sql.Connection;
import javax.sql.DataSource;

/**
 * 事务工厂
 *
 * @author rwj
 * @since 2024/9/23
 */
public interface TransactionFactory {

    /**
     * 根据 Connection 创建 Transaction
     *
     * @param conn Existing database connection
     * @return Transaction
     */
    Transaction newTransaction(Connection conn);

    /**
     * 根据数据源和事务隔离级别创建 Transaction
     *
     * @param dataSource DataSource to take the connection from
     * @param level      Desired isolation level
     * @param autoCommit Desired autocommit
     * @return Transaction
     */
    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit);

}
