package cn.rwj.study.spring.myspring.xiaofuge.factory.supprt;

import cn.rwj.study.spring.myspring.xiaofuge.BeansException;
import cn.rwj.study.spring.myspring.xiaofuge.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @author rwj
 * @since 2023/10/30
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if(null == ctor) {
            return enhancer.create();
        }
        return enhancer.create(ctor.getParameterTypes(), args);
    }
}
