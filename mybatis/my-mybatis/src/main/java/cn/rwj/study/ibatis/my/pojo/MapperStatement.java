package cn.rwj.study.ibatis.my.pojo;

import lombok.Data;

/**
 * @author rwj
 * @since 2023/9/1
 *
 * 映射配置类：mapper.xml解析出来内容
 * 每个pojo实体都会对应一个 mapper.xml文件，即一个MapperStatement对象
 */
@Data
public class MapperStatement {

    //唯一标识 statementId：namespace.id
    private String statementId;

    //返回值类型
    private String resultType;

    //参数类型
    private String parameterType;

    //sql语句
    private String sql;

    // 判断当前是什么操作的一个属性-增删改查
    private String sqlCommandType;

}
