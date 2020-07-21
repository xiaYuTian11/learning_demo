package com.tmw.cglib;

/**
 * @author TMW
 * @date 2020/7/17 15:56
 */
public class CglibProxyTest {
    public static void main(String[] args) {
        MyCgLib myCgLib = new MyCgLib();
        HelloService object = myCgLib.getProxy(HelloService.class);
        object.save();
    }

}
