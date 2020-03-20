package com.tmw.net.tcp;

import java.io.File;

/**
 * @author TMW
 * @since 2020/3/20 11:28
 */
public class FilePathTest {
    public static void main(String[] args) {
        File file = new File("./java_demo/wallhaven-687573.jpg");
        System.out.println(file);
        System.out.println(FilePathTest.class.getClassLoader().getResource("wallhaven-687573.jpg").getPath());
        String property =System.getProperty("user.dir");
        System.out.println(property);
    }
}
