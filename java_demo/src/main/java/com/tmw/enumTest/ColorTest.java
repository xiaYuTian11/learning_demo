package com.tmw.enumTest;

import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * @author TMW
 * @date 2020/6/28 9:53
 */
public class ColorTest {
    @Test
    public void test01() {
        System.out.println(Objects.equals(1, ColorEnum.getColorEnum(33).getColor()));

    }
}
