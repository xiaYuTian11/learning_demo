package com.tmw.aop;

import com.tmw.pointcut.AspectJExpressionPointcut;
import com.tmw.pointcut.Pointcut;

/**
 * 基于AspectJ切入点的通知者实现     用户配的一个切面,包含Advice(功能增强)和Pointcut(切入点)
 *
 * @author TMW
 * @date 2020/7/29 14:26
 */
public class AspectJPointcutAdvisor implements PointcutAdvisor {

    /**
     * 用户配置的advice的bean的名字
     */
    private String adviceBeanName;

    /**
     * 切入点表达式
     */
    private String expression;

    /**
     * AspectJ表达式切入点对象
     */
    private AspectJExpressionPointcut pointcut;

    public AspectJPointcutAdvisor(String adviceBeanName, String expression) {
        super();
        this.adviceBeanName = adviceBeanName;
        this.expression = expression;
        this.pointcut = new AspectJExpressionPointcut(this.expression);
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public String getAdviceBeanName() {
        return this.adviceBeanName;
    }

    @Override
    public String getExpression() {
        return this.expression;
    }
}
