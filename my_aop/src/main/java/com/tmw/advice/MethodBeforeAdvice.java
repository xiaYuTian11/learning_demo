package com.tmw.advice;

import java.lang.reflect.Method;

/**
 * 前置增强
 *
 * @author TMW
 * @date 2020/7/29 11:48
 */
public interface MethodBeforeAdvice extends Advice {
    /**
     * 实现该方法进行前置增强
     *
     * @param method 被增强的方法
     * @param args   被增强的方法的参数
     * @param target 被增强的目标对象(被增强的方法所在的类)
     * @throws Throwable 异常
     */
    void before(Method method, Object[] args, Object target) throws Throwable;
}
