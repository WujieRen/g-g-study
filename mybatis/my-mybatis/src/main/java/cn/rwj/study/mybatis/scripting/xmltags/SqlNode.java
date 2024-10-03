package cn.rwj.study.mybatis.scripting.xmltags;

/**
 * SQL 节点
 *
 * @author rwj
 * @since 2024/10/3
 */
public interface SqlNode {

    boolean apply(DynamicContext context);
    
}
