package com.demo.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TMW
 * @since 2020/3/16 9:50
 */
public class Hello {
    public static void main(String[] args) {
        System.out.println("hello world!");
        // 反射创建对象的三种方式
        Hello hello = new Hello();
        Class<? extends Hello> aClass = hello.getClass();
        Class<Hello> helloClass = Hello.class;
        try {
            Class<?> aClass1 = Class.forName("com.demo.base.Hello");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<Hello> list = new ArrayList<>();
        while (true){
            list.add(new Hello());
        }
    }
}
