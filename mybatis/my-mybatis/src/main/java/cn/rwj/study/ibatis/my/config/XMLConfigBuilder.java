package cn.rwj.study.ibatis.my.config;

import cn.rwj.study.ibatis.my.io.Resources;
import cn.rwj.study.ibatis.my.pojo.Configuration;
import com.alibaba.druid.pool.DruidDataSource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author rwj
 * @since 2023/9/1
 *
 *  XMLConfigBuilder核心配置解析类里面嵌套着XMLMapperBuilder映射配置文件解析类
 *  输入流转化为Document对象，一是根据property标签获取数据库配置信息并创建数据源添加到configuration
 *  二是根据mapper标签通过 XMLMapperBuilder 解析类遍历解析配置文件同样添加到configuration的map集合类
 */
public class XMLConfigBuilder {

    private Configuration configuration;

    public XMLConfigBuilder() {
        this.configuration = new Configuration();
    }

    /**
     * 使用dom4j+xpath解析
     * @param inputStream
     * @return
     * @throws Exception
     */
    public Configuration parse(InputStream inputStream) throws Exception {
        //将xml转化为Document对象
        Document document = new SAXReader().read(inputStream);
        //获取跟节点，对于sqlMapConfig.xml来说就是 <Configuration> 标签
        Element rootElement = document.getRootElement();

        // -------------解析数据库配置文件----------------
        /*
           "//"表示从匹配选择的当前节点，而不考虑它们的位置
           即这里获取数据源url用户密码信息
           例：<property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
         */
        List<Element> propertyList = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        for (Element element : propertyList) {
            // 获取<property>标签中，name和value属性的值
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name,value);
        }
        // 创建数据源对象
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.getProperty("driverClassName"));
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        // 将创建好的数据源添加到Configuration对象中
        configuration.setDataSource(dataSource);

        // -------------解析映射配置文件----------------
        /*
           1.获取映射配置文件路径
           2.根据路径进行映射文件的加载解析
           3.封装到MapperStatement，存入configuration的map集合中
        */
        // 例：<mapper resource="mapper/demo-conf.xml"></mapper>
        List<Element> mapperList = rootElement.selectNodes("//mapper");
        for (Element element : mapperList) {
            String resource = element.attributeValue("resource");
            InputStream resourceAsStream = Resources.getResourceAsStream(resource);
            // XMLMapperBuilder 专门解析映射配置文件的对象-最后会存入configuration的map集合对象中
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
            xmlMapperBuilder.parse(resourceAsStream);
        }
        return configuration;
    }

}
