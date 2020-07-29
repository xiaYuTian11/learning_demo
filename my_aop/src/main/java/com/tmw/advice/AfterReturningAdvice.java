package com.tmw.advice;

import java.lang.reflect.Method;

/**
 * 后置增强
 *
 * @author TMW
 * @date 2020/7/29 11:50
 */
public interface AfterReturningAdvice extends Advice {
    /**
     * 实现该方法，提供后置增强
     *
     * @param returnValue 被增强的方法的返回值
     * @param method      被增强的方法
     * @param args        被增强的方法的参数
     * @param target      被增强的目标对象(被增强的方法所在的类)
     * @throws Throwable 异常
     */
    void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable;
}
