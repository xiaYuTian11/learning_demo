package com.tmw.javaweb;

import java.util.HashMap;
import java.util.Map;

import static cn.hutool.core.util.HexUtil.toHex;

/**
 * @author TMW
 * @since 2020/6/12 10:19
 */
public class CharsetDemo {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");

        Map<String, String> map2 = new HashMap<>();
        map2.put("a", "3");
        map2.put("b", "4");
        map.putAll(map2);
        System.out.println(map);
    }

}
