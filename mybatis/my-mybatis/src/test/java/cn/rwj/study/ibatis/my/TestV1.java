package cn.rwj.study.ibatis.my;

import cn.rwj.study.ibatis.my.io.Resources;
import cn.rwj.study.ibatis.test.model.User;
import cn.rwj.study.ibatis.my.sqlsession.SqlSession;
import cn.rwj.study.ibatis.my.sqlsession.factory.SqlSessionFactory;
import cn.rwj.study.ibatis.my.sqlsession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author rwj
 * @since 2023/9/1
 */
public class TestV1 {


    @Test
    public void test() throws Exception {
        // 1.根据配置文件的路径，加载成字节输入流，存到内存中 注意：配置文件还未解析
        InputStream inputStream = Resources.getResourceAsStream("demo-conf.xml");

        // 2.解析配置文件，封装到Configuration对象，创建sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 3.生产sqlSession 创建执行器对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 4.调用sqlSession方法
        User user = new User();
        user.setId(2);
        user.setName("slb");
        User userOne = sqlSession.selectOne("user.selectOne", user);
        System.out.println("查询单个用户：" + userOne);

        List<User> userList = sqlSession.selectList("user.selectList", null);
        System.out.println("查询所有用户：" + userList.toString());

        // 5.释放资源
        sqlSession.close();
    }

}
