package com.demo.chapter1;

import org.junit.jupiter.api.Test;

import java.io.File;

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
    public void test02(){
        // List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
        //         new Apple(155, "green"),
        //         new Apple(120, "red"));

    }
}
