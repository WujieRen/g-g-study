package cn.rwj.study.spring.springbasic.BeanPostProcessorDemo;

import cn.hutool.core.util.StrUtil;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rwj
 * @since 2023/6/15
 */
public class PlaceHolderResolver {

    private static final String PLACEHOLDER_PREFIX = "${";
    private static final String PLACEHOLDER_SUFFIX = "}";
    private static final String VALUE_SEPARATE = ":";

    /**
     * 提取占位符中的key
     * 支持的格式:
     * ${key}               提取出key
     * ${key:defaultValue}  提取出key和defaultValue
     * @param propertyString
     * @return
     */
    public static Map<String, String> obtainPlaceHolderKey(ConfigurableEnvironment environment, String propertyString) {
        String defaultVal = "";
        Map<String, String> keyAndDefaultValue = new HashMap<>();
        if (propertyString.startsWith(PLACEHOLDER_PREFIX)
                && propertyString.endsWith(PLACEHOLDER_SUFFIX)) {
            String placeholder = propertyString.substring(PLACEHOLDER_PREFIX.length(), propertyString.length()-1);
            defaultVal =  environment.getProperty(placeholder);
            if (placeholder.contains(VALUE_SEPARATE)) {
                String[] split = placeholder.split(VALUE_SEPARATE);
                if (StrUtil.isBlank(split[0])){
                    throw new RuntimeException("Resolve placeholder error! placeholder: "+propertyString);
                }
                keyAndDefaultValue.put(split[0],split[1]);
            }else {
                keyAndDefaultValue.put(placeholder, defaultVal);
            }
        }else {
            keyAndDefaultValue.put(propertyString, defaultVal);
        }
        return keyAndDefaultValue;
    }

}
