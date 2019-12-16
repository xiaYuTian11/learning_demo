package com.demo.jfinal.intercepter;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * @author TMW
 * @version 1.0
 * @date 2019/9/18 21:41
 */
public class MyIntercepter implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        System.out.println("执行拦截器。。。。");
        inv.invoke();
    }
}
