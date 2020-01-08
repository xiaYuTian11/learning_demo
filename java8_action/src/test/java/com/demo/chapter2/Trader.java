package com.demo.chapter2;

import lombok.Data;

/**
 * @author TMW
 * @since 2020/1/6 11:02
 */
@Data
public class Trader {
    private final String name;
    private final String city;

    @Override
    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}
