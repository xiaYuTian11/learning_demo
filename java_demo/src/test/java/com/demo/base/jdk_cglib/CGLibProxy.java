package com.demo.base.jdk_cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author TMW
 * @since 2020/3/19 14:30
 */
public class CGLibProxy implements MethodInterceptor {
    private Object targetObject;

    /**
     * 创建代理类
     *
     * @param obj
     * @return
     */
    public Object createProxyObject(Object obj) {
        this.targetObject = obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObject.getClass());
        enhancer.setCallback(this);
        Object objProxy = enhancer.create();
        return objProxy;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object obj = null;
        if ("addUser".equals(method.getName())) {
            checkPopedom();
        }
        obj = method.invoke(targetObject, objects);
        return obj;
    }

    /**
     * 模拟检查权限的例子
     */
    private void checkPopedom() {
        System.out.println("======cglib   检查权限checkPopedom()======");
    }

}
