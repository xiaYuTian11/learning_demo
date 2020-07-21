package com.tmw.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author TMW
 * @date 2020/7/17 15:36
 */
public class MyCgLib implements MethodInterceptor {

    public <T> T getProxy(Class<T> aClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(aClass);
        enhancer.setCallback(this);
        System.out.println("执行  getObject（）");
        return aClass.cast(enhancer.create());
    }

    // private Enhancer enhancer = new Enhancer();
    // public Object getProxy(Class clazz){
    //     //设置需要创建子类的类
    //     enhancer.setSuperclass(clazz);
    //     enhancer.setCallback(this);
    //     //通过字节码技术动态创建子类实例
    //     return enhancer.create();
    // }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("执行方法");
        Object invoke = proxy.invokeSuper(obj, args);

        return invoke;
    }

}
