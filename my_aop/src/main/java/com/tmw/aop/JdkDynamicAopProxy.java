package com.tmw.aop;

import com.tmw.beans.BeanFactory;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * JDK动态AOP代理实现
 *
 * @author TMW
 * @date 2020/7/29 15:05
 */
@Slf4j
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    // private static final Log logger = LogFactory.getLog(JdkDynamicAopProxy.class);

    private String beanName;
    private Object target;
    private List<Advisor> matchAdvisors;

    private BeanFactory beanFactory;

    public JdkDynamicAopProxy(String beanName, Object target, List<Advisor> matchAdvisors, BeanFactory beanFactory) {
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
        System.out.println("为" + target + "创建代理。");
        return null;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
