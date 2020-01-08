package com.demo.chapter2;

import lombok.Data;

/**
 * @author TMW
 * @since 2020/1/6 11:03
 */
@Data
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    @Override
    public String toString() {
        return "{" + this.trader + ", " +
                "year: " + this.year + ", " +
                "value:" + this.value + "}";
    }

}
