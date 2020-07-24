package com.tmw.proxy;

import java.lang.reflect.Proxy;
@SuppressWarnings("unchecked")
public class ProxyFactory {

    public static <T> T create(Class<T> interfaceClass){
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                new RpcInvoker<T>(interfaceClass)
        );
    }
}
