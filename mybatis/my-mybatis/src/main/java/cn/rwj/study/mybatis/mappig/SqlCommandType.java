package cn.rwj.study.mybatis.mappig;

/**
 * SQL 指令类型
 *
 * @author rwj
 * @since 2024/9/21
 */
public enum SqlCommandType {

    /**
     * 未知
     */
    UNKNOWN,
    /**
     * 插入
     */
    INSERT,
    /**
     * 更新
     */
    UPDATE,
    /**
     * 删除
     */
    DELETE,
    /**
     * 查找
     */
    SELECT;


}
