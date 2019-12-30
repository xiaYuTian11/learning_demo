package com.demo.chapter2;

import lombok.Data;

/**
 * @author TMW
 * @date 2019/12/30 17:59
 */
@Data
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public enum Type {MEAT, FISH, OTHER}
}
