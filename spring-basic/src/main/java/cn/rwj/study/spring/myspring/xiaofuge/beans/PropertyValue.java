package cn.rwj.study.spring.myspring.xiaofuge.beans;

/**
 * @author rwj
 * @since 2023/10/31
 */
public class PropertyValue {

    private final String name;

    /** 属性值 */
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

}
