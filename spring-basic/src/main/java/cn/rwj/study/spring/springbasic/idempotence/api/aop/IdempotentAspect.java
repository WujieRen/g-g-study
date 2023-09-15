package cn.rwj.study.spring.springbasic.idempotence.api.aop;

import cn.rwj.study.spring.springbasic.idempotence.api.common.RedisIdempotentStorage;
import cn.rwj.study.spring.springbasic.idempotence.api.annotation.Idempotent;
import cn.rwj.study.spring.springbasic.idempotence.api.entity.RequestData;
import cn.rwj.study.spring.springbasic.idempotence.api.utils.AopUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author rwj
 * @since 2023/9/15
 */
@Aspect
@Component
public class IdempotentAspect {

    @Resource
    private RedisIdempotentStorage redisIdempotentStorage;

    @Pointcut("@annotation(cn.rwj.study.spring.springbasic.idempotence.api.annotation.Idempotent)")
    public void idempotent() {}

    @Around("idempotent()")
    public Object methodAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Idempotent idempotent = method.getAnnotation(Idempotent.class);

        String field = idempotent.field();
        String name = idempotent.name();
        Class clazzType = idempotent.type();

        String token = "";

        Object object = clazzType.newInstance();
        Map<String, Object> params = AopUtil.getParams(joinPoint);
        if (object instanceof RequestData) {
            RequestData idempotentEntity = (RequestData) params.get(name);
            token = String.valueOf(AopUtil.getFieldValue(idempotentEntity.getHeader(), field));
        }

        if (redisIdempotentStorage.delete(token)) {
            return joinPoint.proceed();
        }

        return  "重复请求";
    }

}
