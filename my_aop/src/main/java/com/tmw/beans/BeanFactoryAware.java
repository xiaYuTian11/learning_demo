package com.tmw.beans;

/**
 * @author TMW
 * @date 2020/7/29 14:39
 */
public interface BeanFactoryAware extends Aware{
    void setBeanFactory(BeanFactory bf);
}
