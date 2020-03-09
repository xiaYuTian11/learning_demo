package com.demo.chapter2;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TMW
 * @since 2020/3/5 11:20
 */
public class Demo03 {
    @Test
    public void test01() {
        Map<String, String> map = new HashMap<>();
        map.put(null, "null0");
        System.out.println(map);
        String s = map.get(null);
        System.out.println(s);
        map.put(null, null);
        System.out.println(map.get(null));
    }

    @Test
    public void test02() {
        System.out.println(2 << 2);
        System.out.println(16 >> 3);
    }

    @Test
    public void test03() throws Exception {
        String str = "hello";

        Method toUpperCase = str.getClass().getMethod("toUpperCase");
        System.out.println(toUpperCase.invoke(str));

    }

}
