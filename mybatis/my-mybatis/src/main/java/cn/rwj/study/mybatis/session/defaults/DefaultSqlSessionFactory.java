package cn.rwj.study.mybatis.session.defaults;

import cn.rwj.study.mybatis.executor.Executor;
import cn.rwj.study.mybatis.mappig.Environment;
import cn.rwj.study.mybatis.session.Configuration;
import cn.rwj.study.mybatis.session.SqlSession;
import cn.rwj.study.mybatis.session.SqlSessionFactory;
import cn.rwj.study.mybatis.session.TransactionIsolationLevel;
import cn.rwj.study.mybatis.transaction.Transaction;
import cn.rwj.study.mybatis.transaction.TransactionFactory;

import java.sql.SQLException;

/**
 * @author rwj
 * @since 2024/9/21
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Transaction tx = null;
        try {
            final Environment environment = configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
            tx = transactionFactory.newTransaction(environment.getDataSource(), TransactionIsolationLevel.READ_COMMITTED, false);
            final Executor executor = configuration.newExecutor(tx);
            return new DefaultSqlSession(configuration, executor);
        } catch (Exception e) {
            try {
                assert tx != null;
                tx.close();
            } catch (SQLException ignore) {
            }
            throw new RuntimeException("Error opening session.  Cause: " + e);
        }
    }
}
