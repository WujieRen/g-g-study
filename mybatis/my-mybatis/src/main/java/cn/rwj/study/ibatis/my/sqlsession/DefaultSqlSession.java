package cn.rwj.study.ibatis.my.sqlsession;

import cn.rwj.study.ibatis.my.executor.Executor;
import cn.rwj.study.ibatis.my.pojo.Configuration;
import cn.rwj.study.ibatis.my.pojo.MapperStatement;

import java.lang.reflect.*;
import java.util.List;
import java.util.Objects;

/**
 * @author rwj
 * @since 2023/9/1
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }


    /**
     * @param statementId
     * @param param       替换sql语句中的占位符？，可能字符串、对象、Map、集合
     * @param <E>
     * @return
     * @throws Exception
     */
    @Override
    public <E> List<E> selectList(String statementId, Object param) throws Exception {
        // 根据StatementId获取映射配置对象MapperStatement
        MapperStatement mapperStatement = configuration.getMapperStatementMap().get(statementId);
        // 然后将具体的查询操作委派给SimpleExecutor执行器
        // 执行底层jdbc需要：1.数据库配置，2.sql配置信息
        return executor.query(configuration, mapperStatement, param);
    }

    @Override
    public <T> T selectOne(String statementId, Object param) throws Exception {
        // 调用selectList()
        List<Object> selectList = selectList(statementId, param);
        if (selectList.size() == 1) {
            return (T) selectList.get(0);
        } else if (selectList.size() > 1) {
            throw new Exception("返回数据不止一条！！！");
        } else {
            return null;
        }
    }

    @Override
    public void close() {
        executor.close();
    }

    /**
     * 这里使用的 JDK 动态代理， mapperClass 必须是接口，或者实现了接口
     * @param mapperClass
     * @return
     * @param <P>
     */
    @Override
    public <P> P getMapper(Class<?> mapperClass) {
        // 使用JDK动态代理生成基于接口的对象
        // 1、创建一个类(代理类)，实现目标接口，实现所有的方法实现
        // 2、动态代理类：代码运行期间生成的，而非编译期
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            // proxy：生成的代理对象本身，很少用
            // method：调用接口中哪个方法，则执行对应代理里的对应方法
            // args：调用方法的参数
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 执行底层JDBC
                // 1.获取statementId
                // ps:约定接口中的方法名要与<select>标签的id属性名一致，这样就可以通过接口获取statementId = namespace.id
                String methodName = method.getName();// 方法名 - findAll
                String className = mapperClass.getName();//接口类名 - cn.rwj.study.ibatis.test.dao.IUserDao
                String statementId = className + "." + methodName;

                // 2.判断调用sqlSession中CRUD的什么方法
                // MapperStatement类添加属性sqlCommandType-sql增删改查类型
                MapperStatement mapperStatement = configuration.getMapperStatementMap().get(statementId);
                if(Objects.isNull(mapperStatement)) {
                    throw new RuntimeException("没找到匹配的的 mapper.xml 文件");
                }
                // select  update delete insert
                String sqlCommandType = mapperStatement.getSqlCommandType();
                switch (sqlCommandType) {
                    case "select":
                        // 3.判断调用selectOne还是selectList
                        // 获取返回值类型，
                        Type genericReturnType = method.getGenericReturnType();
                        // ParameterizedType是Type的子接口，表示一个有参数的类型，例如Collection<T>，Map<K,V>等
                        if (genericReturnType instanceof ParameterizedType) {
                            if (args != null) {
                                return selectList(statementId, args[0]);
                            }
                            return selectList(statementId, null);
                        }
                        return selectOne(statementId, args[0]);
                    case "update":
                        // 执行更新方法调用
                        break;
                    case "delete":
                        // 执行delete方法调用
                        break;
                    case "insert":
                        // 执行insert方法调用
                        break;
                }
                return null;
            }
        });

        return (P) proxyInstance;
    }


}
