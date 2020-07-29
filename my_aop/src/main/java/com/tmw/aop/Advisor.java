package com.tmw.aop;

/**
 * Advisor（通知者）接口
 *
 * @author TMW
 * @date 2020/7/29 14:24
 */
public interface Advisor {
    /**
     * 增强 bean name
     *
     * @return
     */
    String getAdviceBeanName();

    /**
     * 表达式
     *
     * @return
     */
    String getExpression();
}
