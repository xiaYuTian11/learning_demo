package com.tmw;

import cn.hutool.core.io.FileUtil;
import com.tmw.util.ZipUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author TMW
 * @date 2020/6/17 9:20
 */
public class ZipTest {

    public static void main(String[] args) throws Exception {
        // File file = new File("D:\\java\\ideaWorkingSpace\\tmw\\learning_demo\\java_demo\\src\\main\\resources\\data");
        // File[] files = file.listFiles();
        //
        // final Path classPath = Paths.get(ZipTest.class.getClassLoader().getResource(".").toURI());
        // final Path dataPath = classPath.resolve("data.zip");
        // Files.deleteIfExists(dataPath);
        // // Files.createFile(dataPath);
        // // File zipFile = new File("D:\\java\\ideaWorkingSpace\\tmw\\learning_demo\\java_demo\\target\\classes\\data.zip");
        // // FileUtil.del(zipFile);
        // File zipFile = ZipUtil.zipFilePwd(dataPath.toFile(), files);
        // zipFile.deleteOnExit();
        // System.out.println(zipFile.getPath().toString());

        File file = new File("C:\\Users\\Administrator\\Desktop\\测试\\orgData");
        File[] files = file.listFiles();

        // final Path classPath = Paths.get(this.getClass().getClassLoader().getResource(".").toURI());
        // final Path dataPath = classPath.resolve("data.zip");

        File zipFile = new File("C:\\Users\\Administrator\\Desktop\\测试\\测试\\orgData.zip");
        FileUtil.del(zipFile);
        File file1 = ZipUtil.zipFilePwd(zipFile, files);
        System.out.println(file1.getPath().toString());
    }

    @Test
    public void test01() {
        File file = new File("C:\\Users\\Administrator\\Desktop\\测试\\orgData");
        File zipFile = new File("C:\\Users\\Administrator\\Desktop\\测试\\测试\\orgData.zip");
        org.zeroturnaround.zip.ZipUtil.pack(file, zipFile, 5);

        // org.zeroturnaround.zip.ZipUtil.pack

    }

    @Test
    public void test02() {
        File file = new File("C:\\Users\\Administrator\\Desktop\\测试\\测试\\orgData.zip");
        File zipFile = new File("C:\\Users\\Administrator\\Desktop\\测试\\测试\\orgDataPassword.zip");
        File file1 = ZipUtil.zipFilePwd(zipFile, file);
        System.out.println(file1.getPath().toString());

    }

}
