package cn.rwj.study.mybatis.transaction.jdbc;

import cn.rwj.study.mybatis.session.TransactionIsolationLevel;
import cn.rwj.study.mybatis.transaction.Transaction;
import cn.rwj.study.mybatis.transaction.TransactionFactory;
import java.sql.Connection;
import javax.sql.DataSource;

/**
 * @author rwj
 * @since 2024/9/23
 */
public class JdbcTransactionFactory implements TransactionFactory {
    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }
}
