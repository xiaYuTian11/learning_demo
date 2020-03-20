package com.demo.base.jdk_cglib;

/**
 * spring ioc 原理：通过读取配置文件或者注解方式获取到bean对象列表，通过类全限定名称创建对象，
 * 通过动态代理方式（jdk--应用为接口和cglib--应用为类,spring 默认代理实现采用的jdk）来创建代理对象实现IOC功能；
 * <p>
 * jdk和cglib:jdk8之前cglib速度比jdk快，但是从jdk8只有速度都差不多，而且cglib 还需要引入额外的jar包，
 * 所以默认推荐采用jdk方式来实现动态代理
 * </>
 *
 * @author TMW
 * @since 2020/3/19 14:38
 */
public class TestProxy {
    public static void main(String[] args) {
        System.out.println("**********************JDKProxy**********************");
        JDKProxy jdkProxy = new JDKProxy();
        IUserManager newProxy = (IUserManager) jdkProxy.newProxy(new IUserManagerImpl());
        newProxy.addUser("admin", "123456");

        System.out.println("**********************CGLibProxy**********************");
        CGLibProxy cgLibProxy = new CGLibProxy();
        IUserManager proxyObject = (IUserManager) cgLibProxy.createProxyObject(new IUserManagerImpl());
        proxyObject.addUser("tmw", "654321");
    }
}
