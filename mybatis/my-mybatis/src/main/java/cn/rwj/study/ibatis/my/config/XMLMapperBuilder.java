package cn.rwj.study.ibatis.my.config;

import cn.rwj.study.ibatis.my.pojo.Configuration;
import cn.rwj.study.ibatis.my.pojo.MapperStatement;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @author rwj
 * @since 2023/9/1
 *
 *  与XMLConfigBuilder解析类原理一样
 *  传入configuration，并将解析好的MapperStatement对象添加到mapperStatementMap
 */
public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream resourceAsStream) throws Exception {
        // 将输入流转化为Document对象，并获取跟节点<mapper>
        Document document = new SAXReader().read(resourceAsStream);
        Element rootElement = document.getRootElement();

        // 例：<mapper namespace="user">
        String namespace = rootElement.attributeValue("namespace");
        /* 例：
            <select id="selectOne" resultType="com.xc.pojo.User" parameterType="com.xc.pojo.User" >
                select * from user where id = #{id} and name = #{name}
            </select>
        */
        List<Element> selectList = rootElement.selectNodes("//select");
        for (Element element : selectList) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String parameterType = element.attributeValue("parameterType");
            String sql = element.getTextTrim();

            // 封装MapperStatement对象
            MapperStatement mapperStatement = new MapperStatement();
            String statementId = namespace + "." + id;
            mapperStatement.setStatementId(statementId);
            mapperStatement.setParameterType(parameterType);
            mapperStatement.setResultType(resultType);
            mapperStatement.setSql(sql);
            mapperStatement.setSqlCommandType("select");

            // 添加到configurations的map集合中
            configuration.getMapperStatementMap().put(statementId,mapperStatement);
        }
    }

}
