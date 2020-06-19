package com.demo.base;

/**
 * @author TMW
 * @date 2020/6/16 11:05
 */
public class JVMTest {
    public void stack(String[] arg) {
        String str = "junshan";
        if (str.equals("junshan")) {
            int i = 3;
            while (i > 0) {
                long j = i;
                i--;
            }
        } else {
            char b = 'a';
            System.out.println(b);
        }

    }
}
