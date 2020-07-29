package com.tmw.pointcut;

import java.lang.reflect.Method;

/**
 * Pointcut标准接口
 *
 * @author TMW
 * @date 2020/7/29 11:56
 */
public interface Pointcut {
    /**
     * 匹配类
     *
     * @param targetClass
     * @return
     */
    boolean matchsClass(Class<?> targetClass);

    /**
     * 匹配方法
     *
     * @param method
     * @param targetClass
     * @return
     */
    boolean matchsMethod(Method method, Class<?> targetClass);
}
