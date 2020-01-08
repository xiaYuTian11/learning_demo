package com.demo.chapter2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author TMW
 * @date 2019/12/30 18:01
 */
public class Demo01 {
    static List<Dish> menu;

    static {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

    }

    @Test
    public void test01() {
        Map<String, String> map = new HashMap<>();
        String s = map.get(null);
        System.out.println(s);
    }

    @Test
    public void test02() {
        String[] words = new String[]{"Hello", "World"};
        List<String[]> collect = Arrays.stream(words).map(word -> word.split("")).distinct().collect(Collectors.toList());
        System.out.println(Arrays.asList(collect));
        List<Stream<String>> streamList = Arrays.stream(words).map(word -> word.split("")).map(Arrays::stream).collect(Collectors.toList());
        List<String> stringList = Arrays.stream(words).map(word -> word.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(stringList);
    }

    @Test
    public void test03() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<Stream<Integer[]>> streamList = numbers1.stream().map(num1 -> numbers2.stream().map(num2 -> new Integer[]{num1, num2})).collect(Collectors.toList());
        List<Integer[]> collect = numbers2.stream().flatMap(num1 -> numbers2.stream().map(num2 -> new Integer[]{num1, num2})).collect(Collectors.toList());

    }

}
