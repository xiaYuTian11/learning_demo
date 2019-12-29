package com.demo.chapter1;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author TMW
 * @date 2019/12/26 10:27
 */
public class Demo01 {
    @Test
    public void test01() {
        File[] files = new File(".").listFiles(File::isHidden);
    }

    @Test
    public void test02() {
        // List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
        //         new Apple(155, "green"),
        //         new Apple(120, "red"));

    }

    @Test
    public void test03() {
        System.out.println(filter("String", str -> str.equals("String1")));
    }

    public boolean filter(String str, Predicate<String> predicate) {
        return predicate.test(str);
    }

    @Test
    public void test04() {
        List<String> list = new ArrayList<>();
        Predicate<String> p = s -> list.add(s);
        Consumer<String> c = s -> list.add(s);
        System.out.println(p);
        System.out.println(c);
        System.out.println(list);
    }
}
