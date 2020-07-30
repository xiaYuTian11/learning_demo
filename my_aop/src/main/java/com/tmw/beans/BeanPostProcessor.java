package com.tmw.beans;

/**
 * AOP增强处理接口
 *
 * @author TMW
 * @date 2020/7/29 14:35
 */
public interface BeanPostProcessor {
    /**
     * bean初始化前增强
     *
     * @param bean
     * @param beanName
     * @return
     * @throws Throwable
     */
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws Throwable {
        return bean;
    }

    /**
     * bean初始化后增强
     *
     * @param bean
     * @param beanName
     * @return
     * @throws Throwable
     */
    default Object postProcessAfterInitialization(Object bean, String beanName) throws Throwable {
        return bean;
    }
}
