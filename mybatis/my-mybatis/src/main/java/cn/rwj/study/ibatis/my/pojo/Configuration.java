package cn.rwj.study.ibatis.my.pojo;

import lombok.Data;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rwj
 * @since 2023/9/1
 *
 * 核心配置类：数据库配置信息以及映射配置类的map集合
 * 将多个 MapperStatement 对象存入 Map 集合，statementId(namespace.id) 作为 key
 * 将所有的配置文件都聚合到 Configuration 中，方便一次读取以及统一管理
 *
 */
@Data
public class Configuration {

    //数据源对象
    private DataSource dataSource;

    //map.xml对象集合 key：statementId
    private Map<String,MapperStatement> mapperStatementMap = new HashMap<>();

}
