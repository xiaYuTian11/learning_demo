package com.tmw.aop;

import com.tmw.beans.BeanFactory;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author TMW
 * @date 2020/7/29 15:38
 */
@Slf4j
public class CglibDynamicAopProxy implements AopProxy, MethodInterceptor {
    // private static final Log logger = LogFactory.getLog(CglibDynamicAopProxy.class);
    private static Enhancer enhancer = new Enhancer();

    private String beanName;
    private Object target;

    private List<Advisor> matchAdvisors;

    private BeanFactory beanFactory;

    public CglibDynamicAopProxy(String beanName, Object target, List<Advisor> matchAdvisors, BeanFactory beanFactory) {
        super();
        this.beanName = beanName;
        this.target = target;
        this.matchAdvisors = matchAdvisors;
        this.beanFactory = beanFactory;
    }

    @Override
    public Object getProxy() {
        return this.getProxy(target.getClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        System.out.println("为" + target + "创建cglib代理。");
        Class<?> superClass = this.target.getClass();
        enhancer.setSuperclass(superClass);
        enhancer.setInterfaces(this.getClass().getInterfaces());
        enhancer.setCallback(this);
        Constructor<?> constructor = null;
        try {
            constructor = superClass.getConstructor(new Class<?>[]{});
        } catch (NoSuchMethodException | SecurityException e) {

        }
        if (constructor != null) {
            return enhancer.create();
        }
        //没有无参构造函数时,从BeanDefinition里面获取构造参数的类型和值进行增强
        // else {
        //     BeanDefinition bd = ((DefaultBeanFactory) beanFactory).getBeanDefinition(beanName);
        //     return enhancer.create(bd.getConstructor().getParameterTypes(), bd.getConstructorArgumentRealValues());
        // }

        return null;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return AopProxyUtils.applyAdvices(target, method, objects, matchAdvisors, proxy, beanFactory);
    }
}
