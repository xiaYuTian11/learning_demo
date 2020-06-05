package com.demo.base;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Test
    public void test01(){
        String one = "02";
        String two = null;
        String three = "13";

        String join = String.join(",", one, two, three);
        System.out.println(join);
        List<String> stringList = Stream.of(one, two, three).filter(StrUtil::isNotBlank).collect(Collectors.toList());
        join = String.join(",",stringList);
        System.out.println(join);
        if (StrUtil.isBlank(one) && StrUtil.isBlank(two) && StrUtil.isBlank(three)) {
            // 群众
            System.out.println(join);
        }

    }
}
