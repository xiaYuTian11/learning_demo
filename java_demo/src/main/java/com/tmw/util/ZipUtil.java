package com.tmw.util;

import cn.hutool.core.io.FileUtil;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.util.InternalZipConstants;

import java.io.File;

/**
 * @author TMW
 * @date 2020/6/16 16:24
 */
public class ZipUtil {

    private static final String PASSWORD = "20191809";

    // /**
    //  * 加密压缩ZIP
    //  *
    //  * @param zipFile
    //  */
    // public static File zipFilePwd(File zipFile, File... files) {
    //     try {
    //         // 加密压缩
    //         String zipFilePath = zipFile.getPath();
    //         ZipParameters parameters = new ZipParameters();
    //         // 压缩方式
    //         parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
    //         // 压缩级别
    //         parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
    //         parameters.setEncryptFiles(true);
    //         parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
    //         // 加密方式
    //         parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
    //         parameters.setPassword(PASSWORD.toCharArray());
    //         ZipFile zip = new ZipFile(zipFilePath);
    //         for (File file : files) {
    //             if (file.isFile()) {
    //                 // 文件
    //                 zip.addFile(file, parameters);
    //             } else {
    //                 zip.addFolder(file, parameters);
    //             }
    //         }
    //         return zip.getFile();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return zipFile;
    // }

    /**
     * 加密压缩ZIP
     *
     * @param destFile
     */
    public static File zipFilePwd(File destFile, File... srcFiles) {
        try {
            // 加密压缩
            // String zipFilePath = zipFile.getPath();
            ZipParameters parameters = new ZipParameters();
            // // 压缩方式
            // parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            // // 压缩级别
            // parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
            parameters.setEncryptFiles(true);
            // parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
            // // 加密方式
            parameters.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);
            // parameters.setPassword(PASSWORD.toCharArray());

            ZipFile zip = new ZipFile(destFile, PASSWORD.toCharArray());

            zip.setCharset(InternalZipConstants.CHARSET_UTF_8);
            // ZipFile zip = new ZipFile(zipFilePath);
            for (File file : srcFiles) {
                if (file.isFile()) {
                    // 文件
                    zip.addFile(file, parameters);
                } else {
                    zip.addFolder(file, parameters);
                }
            }
            return zip.getFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return destFile;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\Administrator\\Desktop\\测试\\data.zip");
        File[] files = file.listFiles();

        // final Path classPath = Paths.get(this.getClass().getClassLoader().getResource(".").toURI());
        // final Path dataPath = classPath.resolve("data.zip");

        File zipFile = new File("C:\\Users\\Administrator\\Desktop\\测试\\测试\\dataPassword.zip");
        FileUtil.del(zipFile);
        File file1 = zipFilePwd(zipFile, files);
        System.out.println(file1.getPath().toString());
    }

}
