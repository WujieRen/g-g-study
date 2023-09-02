package cn.rwj.study.ibatis.my.executor;

import cn.rwj.study.ibatis.my.config.BoundSql;
import cn.rwj.study.ibatis.my.pojo.Configuration;
import cn.rwj.study.ibatis.my.pojo.MapperStatement;
import cn.rwj.study.ibatis.my.utils.GenericTokenParser;
import cn.rwj.study.ibatis.my.utils.ParameterMapping;
import cn.rwj.study.ibatis.my.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rwj
 * @since 2023/9/1
 */
public class SimpleExecutor implements Executor {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    @Override
    public <E> List<E> query(Configuration configuration, MapperStatement mapperStatement, Object param) throws Exception {
        // 1.加载驱动，获取数据库连接
        connection = configuration.getDataSource().getConnection();

        // 2.获取preparedStatement预编译对象
        // 从mapperStatement中获取sql语句
        // 例：select * from user where id = #{id} and username = #{username}
        String sql = mapperStatement.getSql();

        // 1)需要转化为：select * from user where id = ? and username = ?
        // 2)需要将替换的值保存下来，后续?赋值需要用到
        BoundSql boundSql = getBoundSql(sql);
        String finalSql = boundSql.getFinalSql();
        preparedStatement = connection.prepareStatement(finalSql);

        // 3.根据入参赋值？
        // 获取入参的全限定类名 com.xc.pojo.User
        String parameterType = mapperStatement.getParameterType();
        // 没有入参类型，表示sql没有参数，这里不需要?赋值
        if (parameterType != null) {
            // 入参对象Class类
            Class<?> parameterTypeClass = Class.forName(parameterType);
            // 获取#{}参数字段list
            List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
            for (int i = 0; i < parameterMappingList.size(); i++) {
                // 获取字段名称
                ParameterMapping parameterMapping = parameterMappingList.get(i);
                String paramName = parameterMapping.getContent();
                // 通过反射获取入参对象的Field属性
                Field field = parameterTypeClass.getDeclaredField(paramName);
                // 禁用安全检查，不用排除private，效率提升好多倍
                field.setAccessible(true);
                // param为User对象，通过属性反射获取到user对象id和name的属性值
                Object value = field.get(param);
                // 赋值占位符，占位符？数字从1开始
                preparedStatement.setObject(i + 1, value);
            }
        }

        // 4.执行sql,发起查询
        resultSet = preparedStatement.executeQuery();

        // 5.处理返回结果集
        List<E> list = new ArrayList<>();
        while (resultSet.next()) {
            // 通过<select>标签的返回值类型，创建返回对象
            String resultType = mapperStatement.getResultType();
            Class<?> resultTypeClass = Class.forName(resultType);
            Object obj = resultTypeClass.newInstance();

            // 结果集的元数据信息：字段名，字段值等
            // resultSet: 一条结果集对应一张表的所有字段
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                //数据库字段名
                String columnName = metaData.getColumnName(i);
                // 字段的值
                Object value = resultSet.getObject(columnName);
                // columnName：数据库字段，而下方需要实体中的字段，如果两边不一样，则这需要有一个转化
                // 获取读写方法即get、set方法
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                // 获取读方法-字段的set方法
                Method writeMethod = propertyDescriptor.getWriteMethod();
                // 反射为返回对象赋值
                // 参数1：实例对象 参数2：要设置的值
                writeMethod.invoke(obj, value);
            }
            list.add((E) obj);
        }

        return list;
    }

    /**
     * @Description
     *
     * 1、将<select>标签中的sql的 "#{字段名}" 整个部分替换成？，赋值 finalSql
     *      获取?占位符的sql，以及保存#{}中的字段名称 (这里使用的mybatis代码，不用深究就是根据正则表达式替换)
     * 2、截取#{}中的字段名称，添加到ParameterMapping对象的content属性，多个，赋值List<ParameterMapping>
     *      将替换?时候的字段名取出来放到ParameterMapping对象中，有多个，根据?次序，放入parameterMappingList集合
     *
     * @Author xuchang
     * @Date 2022/10/19 16:44:51
     */
    private BoundSql getBoundSql(String sql) {
        // 1.创建标记处理器：配合标记解析器完成标记的处理解析工作
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        // 2.创建标记解析器
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        // #{}占位符替换成？ 2.解析替换的过程中 将#{}里面的值保存下来 ParameterMapping
        String finalSql = genericTokenParser.parse(sql);
        // #{}里面的值的一个集合 id username
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        return new BoundSql(finalSql, parameterMappings);
    }

    @Override
    public void close() {
// 释放资源
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
