package com.tmw.aop;

/**
 * JDK动态代理和cglib动态代理抽象出公共部分的接口去获取代理对象
 *
 * @author TMW
 * @date 2020/7/29 15:05
 */
public interface AopProxy {
    /**
     * 获取代理对象
     *
     * @return
     */
    Object getProxy();

    /**
     * 通过类加载器获取代理对象
     *
     * @param classLoader
     * @return
     */
    Object getProxy(ClassLoader classLoader);

}
