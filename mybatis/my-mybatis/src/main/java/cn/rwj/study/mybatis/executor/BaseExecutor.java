package cn.rwj.study.mybatis.executor;

import cn.rwj.study.mybatis.mappig.BoundSql;
import cn.rwj.study.mybatis.mappig.MappedStatement;
import cn.rwj.study.mybatis.session.Configuration;
import cn.rwj.study.mybatis.session.ResultHandler;
import cn.rwj.study.mybatis.session.RowBounds;
import cn.rwj.study.mybatis.transaction.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * 执行器抽象基类
 *
 * @author rwj
 * @since 2024/10/2
 */
@Slf4j
public abstract class BaseExecutor implements Executor {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(BaseExecutor.class);

    protected Configuration configuration;
    protected Transaction transaction;
    protected Executor wrapper;

    private boolean closed;

    protected BaseExecutor(Configuration configuration, Transaction transaction) {
        this.configuration = configuration;
        this.transaction = transaction;
        this.wrapper = this;
    }

    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        if (closed) {
            throw new RuntimeException("Executor was closed.");
        }
        return doQuery(ms, parameter, rowBounds, resultHandler, boundSql);
    }

    protected abstract <E> List<E> doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql);

    @Override
    public Transaction getTransaction() {
        if (closed) {
            throw new RuntimeException("Executor was closed.");
        }
        return transaction;
    }

    @Override
    public void commit(boolean required) throws SQLException {
        if (closed) {
            throw new RuntimeException("Cannot commit, transaction is already closed");
        }
        if (required) {
            transaction.commit();
        }
    }

    @Override
    public void rollback(boolean required) throws SQLException {
        if (!closed) {
            if (required) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void close(boolean forceRollback) {
        try {
            try {
                rollback(forceRollback);
            } finally {
                transaction.close();
            }
        } catch (SQLException e) {
            logger.warn("Unexpected exception on closing transaction.  Cause: " + e);
        } finally {
            transaction = null;
            closed = true;
        }
    }

}
