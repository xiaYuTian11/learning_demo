package com.demo.base.jdk_cglib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author TMW
 * @since 2020/3/19 14:26
 */
public class JDKProxy implements InvocationHandler {

    /**
     * 需要代理的目标对象
     */
    private Object targetObject;

    /**
     * 返回代理对象
     *
     * @param targetObject
     * @return
     */
    public Object newProxy(Object targetObject) {
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        checkPopedom();
        Object invoke = method.invoke(targetObject, args);
        return invoke;
    }

    /**
     * 模拟检查权限的例子
     */
    private void checkPopedom() {
        System.out.println("======jdk  检查权限checkPopedom()======");
    }

}
