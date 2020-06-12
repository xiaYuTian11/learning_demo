package com.demo.base;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author TMW
 * @date 2020/6/8 10:40
 */
public class Mian {

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("1A21");
        list.add("1A99");
        list.add("1A22");
        list.add("1A31");

        list.add("1A41");
        list.add("1A42");
        list.add("1A98");
        list.add("1A32");

        List<String> list1 = list.stream().sorted(Comparator.comparing(str -> str)).collect(Collectors.toList());
        System.out.println(list1);

        String year = "2019-12-05 10:50:13.0";
        DateTime parse = DateUtil.parse(year, "yyyy-MM-dd HH:mm:ss.S");
        Date date = parse.toJdkDate();
        parse = DateUtil.parse(DateUtil.format(date, DatePattern.NORM_DATE_PATTERN));
        System.out.println(parse.toDateStr());

        AtomicInteger atomicInteger = new AtomicInteger();
        int one = 10;
        for (int i = 0; i < 10; i++) {
            System.out.println(atomicInteger.addAndGet(one));
        }
        System.out.println(atomicInteger.get());
    }

    @Test
    public void test02() {
        // List<Map<String, String>> list = new ArrayList<>();
        ExecutorService executorService = ThreadUtil.newExecutor();

        // for (int i = 0; i < 100; i++) {
        //     final int ii = i;
        //     executorService.execute(() -> map.put(String.valueOf(ii), String.valueOf(ii)));
        // }
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < 100000000; i++) {
            Map<String, String> map = new HashMap<>();
            map.put(String.valueOf(i), String.valueOf(i));
            list.add(map);
            System.out.println(map);
        }
        System.out.println(list);
    }

}
