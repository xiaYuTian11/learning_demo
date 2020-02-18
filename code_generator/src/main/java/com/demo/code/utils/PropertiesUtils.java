package com.demo.code.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * 配置文件工具类
 *
 * @author tmw
 */
public class PropertiesUtils {

    public static Map<String, String> customMap = new HashMap<>();

    static {
        // 自定义路径名称
        File dir = new File("code_generator/properties");
        try {
            List<File> files = FileUtils.searchAllFile(new File(dir.getAbsolutePath()));
            for (File file : files) {
                if (file.getName().endsWith(".properties")) {
                    Properties prop = new Properties();
                    prop.load(new FileInputStream(file));
                    prop.forEach((k, v) -> customMap.put(String.valueOf(k), String.valueOf(v)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PropertiesUtils.customMap.forEach((k, v) -> {
            System.out.println(k + "--" + v);
        });
    }
}
