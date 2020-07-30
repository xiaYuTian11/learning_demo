package com.tmw.aop;

import com.tmw.pointcut.Pointcut;

/**
 * 基于切入点的通知者实现
 *
 * @author TMW
 * @date 2020/7/29 14:25
 */
public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();

}
