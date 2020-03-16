package com.demo.base;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author TMW
 * @since 2020/3/14 14:15
 */
public class BinarySearch {

    @Test
    public void test() {
        int[] ints = new int[]{9, 10, 8, 17, 55, 21, 33};
        Arrays.parallelSort(ints);
        for (int anInt : ints) {
            System.out.println(anInt);
        }

        int key = 34;
        int recurve = recurve(ints, key, 0, ints.length);
        System.out.println(recurve);
    }

    /**
     * 递归
     *
     * @return
     */
    public int recurve(int[] ints, int key, int be, int af) {
        if (ints[be] > key) {
            return -1;
        }

        if (ints[af - 1] < key) {
            return af;
        }

        int i = (af + be) / 2;

        if (ints[i] > key) {
            if (ints[i - 1] < key) {
                return i;
            }
            return recurve(ints, key, 0, i - 1);
        } else if (ints[i] < key) {
            if (ints[i + 1] > key) {
                return i + 1;
            }
            return recurve(ints, key, i + 1, ints.length);
        } else {
            return i;
        }
    }
}
