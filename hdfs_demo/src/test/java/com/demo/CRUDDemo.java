package com.demo;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

/**
 * 文件系统增删改查
 *
 * @author TMW
 * @since 2020/2/23 17:17
 */
public class CRUDDemo {
    private static FileSystem fileSystem;

    @BeforeAll
    public static void init() throws Exception {
        fileSystem = FileSystem.get(new URI("hdfs://192.168.119.100:8020/"), new Configuration());
    }

    @AfterAll
    public static void cancel() throws IOException {
        fileSystem.close();
    }

    @Test
    public void test() throws Exception {
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.119.100:8020/"), new Configuration());
        RemoteIterator<LocatedFileStatus> listFiles = fileSystem.listFiles(new Path("/"), true);
        while (listFiles.hasNext()) {
            LocatedFileStatus fileStatus = listFiles.next();
            System.out.println("文件存储路径：" + fileStatus.getPath() +
                    "-----------名称：" + fileStatus.getPath().getName());
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            System.out.println("block的个数" + blockLocations.length);
            for (BlockLocation blockLocation : blockLocations) {
                String[] cachedHosts = blockLocation.getCachedHosts();
                for (String cachedHost : cachedHosts) {
                    System.out.println("文件地址：" + cachedHost);
                }
            }
        }
    }

    @Test
    public void test02() throws Exception {
        fileSystem.mkdirs(new Path("/demo"));
        fileSystem.create(new Path("/demo/a.txt"));
    }

    /**
     * 下载
     *
     * @throws Exception
     */
    @Test
    public void test03() throws Exception {
        FSDataInputStream inputStream = fileSystem.open(new Path("/demo/a.txt"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\test\\a.txt"));
        IOUtils.copy(inputStream, fileOutputStream);
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(fileOutputStream);
    }

    /**
     * 上传
     *
     * @throws IOException
     */
    @Test
    public void test04() throws IOException {
        fileSystem.copyFromLocalFile(new Path("D:\\test\\wordcount.txt"),
                new Path("/wordcount"));
    }

    /**
     * 合并文件上传
     *
     * @throws Exception
     */
    @Test
    public void mergeFile() throws Exception {
        //获取分布式文件系统
        FSDataOutputStream outputStream = fileSystem.create(new Path("/bigfile.txt"));
        //获取本地文件系统
        LocalFileSystem local = FileSystem.getLocal(new Configuration());
        //通过本地文件系统获取文件列表，为一个集合
        FileStatus[] fileStatuses = local.listStatus(new Path("D:\\test"));
        for (FileStatus fileStatus : fileStatuses) {
            FSDataInputStream inputStream = local.open(fileStatus.getPath());
            IOUtils.copy(inputStream, outputStream);
            IOUtils.closeQuietly(inputStream);
        }
        IOUtils.closeQuietly(outputStream);
        local.close();
    }
}
