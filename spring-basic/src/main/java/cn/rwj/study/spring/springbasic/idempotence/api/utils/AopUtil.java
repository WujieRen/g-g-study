package cn.rwj.study.spring.springbasic.idempotence.api.utils;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.CodeSignature;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rwj
 * @since 2023/9/15
 */
public final class AopUtil {

    private AopUtil(){}

    public static Object getFieldValue(Object obj, String name) throws Exception {
        Field[] fields = obj.getClass().getDeclaredFields();
        Object object = null;
        for (Field field : fields) {
            field.setAccessible(true);
            if(StringUtils.equalsIgnoreCase(field.getName(), name)) {
                object = field.get(obj);
                break;
            }
        }
        return object;
    }

    public static Map<String, Object> getParams(ProceedingJoinPoint joinPoint) {
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        HashMap<String, Object> param  = new HashMap<>(paramNames.length);

        for (int i = 0; i < paramNames.length; i++) {
            param.put(paramNames[i], paramValues[i]);
        }

        return param;
    }

}
