package cn.rwj.study.mybatis.mappig;

import cn.rwj.study.mybatis.executor.keygen.Jdbc3KeyGenerator;
import cn.rwj.study.mybatis.executor.keygen.KeyGenerator;
import cn.rwj.study.mybatis.executor.keygen.NoKeyGenerator;
import cn.rwj.study.mybatis.scripting.LanguageDriver;
import cn.rwj.study.mybatis.session.Configuration;

import java.util.Collections;
import java.util.List;

/**
 * 映射语句类
 *
 * @author rwj
 * @since 2024/9/21
 */
public class MappedStatement {

    private String resource;
    private Configuration configuration;
    private String id;
    private SqlCommandType sqlCommandType;
    private SqlSource sqlSource;
    Class<?> resultType;
    private LanguageDriver lang;
    private List<ResultMap> resultMaps;
    // step-14 新增
    private KeyGenerator keyGenerator;
    private String[] keyProperties;
    private String[] keyColumns;

    MappedStatement() {
        // constructor disabled
    }

    /**
     * step-11 新增方法
     */
    public BoundSql getBoundSql(Object parameterObject) {
        // 调用 SqlSource#getBoundSql
        return sqlSource.getBoundSql(parameterObject);
    }

    /**
     * 建造者
     */
    public static class Builder {

        private MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuration, String id, SqlCommandType sqlCommandType, SqlSource sqlSource, Class<?> resultType) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.sqlSource = sqlSource;
            mappedStatement.resultType = resultType;
            mappedStatement.keyGenerator = configuration.isUseGeneratedKeys() && SqlCommandType.INSERT.equals(sqlCommandType) ? new Jdbc3KeyGenerator() : new NoKeyGenerator();
            mappedStatement.lang = configuration.getDefaultScriptingLanguageInstance();
        }

        public MappedStatement build() {
            assert mappedStatement.configuration != null;
            assert mappedStatement.id != null;
            mappedStatement.resultMaps = Collections.unmodifiableList(mappedStatement.resultMaps);
            return mappedStatement;
        }

        public Builder resource(String resource) {
            mappedStatement.resource = resource;
            return this;
        }

        public String id() {
            return mappedStatement.id;
        }

        public Builder resultMaps(List<ResultMap> resultMaps) {
            mappedStatement.resultMaps = resultMaps;
            return this;
        }

        public Builder keyGenerator(KeyGenerator keyGenerator) {
            mappedStatement.keyGenerator = keyGenerator;
            return this;
        }

        public Builder keyProperty(String keyProperty) {
            mappedStatement.keyProperties = delimitedStringToArray(keyProperty);
            return this;
        }

    }

    private static String[] delimitedStringToArray(String in) {
        if (in == null || in.trim().length() == 0) {
            return null;
        } else {
            return in.split(",");
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getId() {
        return id;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public SqlSource getSqlSource() {
        return sqlSource;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public LanguageDriver getLang() {
        return lang;
    }

    public List<ResultMap> getResultMaps() {
        return resultMaps;
    }

    public String[] getKeyColumns() {
        return keyColumns;
    }

    public String[] getKeyProperties() {
        return keyProperties;
    }

    public KeyGenerator getKeyGenerator() {
        return keyGenerator;
    }

    public String getResource() {
        return resource;
    }

}
