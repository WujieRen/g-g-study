package cn.rwj.study.spring.myspring.xiaofuge.aop.aspectj;

import cn.rwj.study.spring.myspring.xiaofuge.aop.Pointcut;
import cn.rwj.study.spring.myspring.xiaofuge.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

import java.util.Objects;

/**
 * @author rwj
 * @since 2023/11/14
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut;

    private String expression;

    private Advice advice;

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (Objects.isNull(pointcut)) {
            this.pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }
}
