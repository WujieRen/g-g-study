package cn.rwj.study.mybatis.datasource;

import java.util.Properties;
import javax.sql.DataSource;

/**
 * 数据源工厂
 *
 * @author rwj
 * @since 2024/9/23
 */
public interface DataSourceFactory {

    void setProperties(Properties props);

    DataSource getDataSource();

}
