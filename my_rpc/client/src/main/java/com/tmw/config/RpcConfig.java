package com.tmw.config;

import com.tmw.annotation.RpcInterface;
import com.tmw.proxy.ProxyFactory;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * @author zhengxin
 */
@Configuration
@Slf4j
public class RpcConfig implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Reflections reflections = new Reflections("com.tmw");
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(RpcInterface.class);
        for (Class<?> aClass : typesAnnotatedWith) {
            beanFactory.registerSingleton(aClass.getSimpleName(), ProxyFactory.create(aClass));
        }
        log.info("afterPropertiesSet is {}", typesAnnotatedWith);
    }
}
