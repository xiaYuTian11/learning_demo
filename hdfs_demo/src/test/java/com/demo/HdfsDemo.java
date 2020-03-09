package com.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;

/**
 * 获取文件系统
 *
 * @author TMW
 * @since 2020/2/23 16:48
 */
public class HdfsDemo {

    @Test
    public void test01() throws IOException {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://192.168.119.100:8020/");
        FileSystem fileSystem = FileSystem.get(configuration);
        System.out.println(fileSystem);
    }

    @Test
    public void test02() throws Exception {
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.119.100:8020/"), new Configuration());
        System.out.println(fileSystem);
    }

    @Test
    public void test03() throws IOException {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://192.168.119.100:8020/");
        FileSystem fileSystem = FileSystem.newInstance(configuration);
        System.out.println(fileSystem);
    }
}
