package com.tmw.beans;

/**
 * IOC容器(bean工厂)接口:负责创建bean实例
 *
 * @author TMW
 * @date 2020/7/29 14:42
 */
public interface BeanFactory {
    /**
     * 获取bean
     *
     * @param name bean的名字
     * @return bean 实例
     * @throws Exception
     */
    Object getBean(String name) throws Throwable;

    /**
     * 注册AOP织入(注册AOP增强处理的观察者实现)
     *
     * @param bpp
     */
    void registerBeanPostProcessor(BeanPostProcessor bpp);
}
