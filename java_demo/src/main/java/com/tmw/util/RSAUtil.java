package com.tmw.util;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * rsa 加解密工具类
 *
 * @author TMW
 * @date 2020/6/23 10:22
 */
public class RSAUtil {

    /**
     * 私钥
     */
    private final static String PRIVATEKEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDBK5/Uu7Ekh+Bg\n" +
            "tol6EyNnTd4Plfh+pXFNbFMPhvosoDT39WQgv9QfTnClULKdhSEZWWJbiK9KKkD9\n" +
            "Qn5clW/lI9ljHpgCnAft/23kfXVWdaYmbgJCWhNFekndp/NhgR1nnIym5Gym5E/L\n" +
            "yrbbdtydm6+ReJkqhfe4nakbnsYMwdEkIbAR8vRB78yS008ECp0p40LyyYrX2wYj\n" +
            "Yi7CcoPfavloQ+zXkke6PtHR+AovmvPkt2QMVR19c7AJZEvknLkGliKI9qUo+Cz9\n" +
            "WXmjfTbc/zWZGwzd564jzycySwBupIlRjEGFIriMwS1naFVijnj8oL++F3JF1Ceb\n" +
            "y4SvYvalAgMBAAECggEBAI96fZ8fQjYmDo0HSBm6OWOzvKrK8+XtsLiXKyKvWQbo\n" +
            "gC9kv1hr43zVYGoKBIFnruwLLjlazwhBVkBGsWDHRnNmK0OqJ7JYfrNSNCew4AS2\n" +
            "IhENSPRLv2CtskvHQck8g8C/0ODtbZ6Qmox+J0/fe4I9byyX7PxewV8JC+COEADM\n" +
            "cNnjGgOv/+UcUiP5wSuAn5vVlzsa0AQT6Wv2+5G3WPuTXZHxZOFYrRya7PdpddoP\n" +
            "g2ZODt65ULmsxgpHJ5XeD1NRLPiJOwRbn5prGUcr6kgE+mFoOijqnq5SyHx9SxJU\n" +
            "Z+M7ccIufiw3GsKMXwLzL1B+dLa+Pc7rbfmZKDOXnskCgYEA+C6OBVJVU7c3L6c0\n" +
            "tswEqeUEs4DBeDzgd4yf3adXHzodY4UhF/TjQ2/x+tpi/VWG56kV5rvHC7i/XY5q\n" +
            "E2XX/tazB3/h4w5WH4nK3OxxKYWaeK6d6IFhKkOZQMhlI1XZb1IAWN8bSWNlJODH\n" +
            "d/AWhXvDSB42JL6zPbCM5CnNX88CgYEAx0FvAFyWd2u5Id4as7+lCosM4w+HgQpt\n" +
            "y8CWX+lkxjTtBfjER7J4lUvowyODmsO7DpcGgnmVyDXJfyJ7fSQ1of+rZnZXj56M\n" +
            "MjZBAC7qYvWEFx3+JhdFKxZsdAMbDlWEYHCZOuGKA7nZtXzBpvo8MkxRYYJZy5d3\n" +
            "mWXA5Q3GC0sCgYBGHzlHbaToO2sfTroJMAndSvUSFr4TT/gILzZ4NTDbhNsK5HGe\n" +
            "SDXyNu4C97NiasjsKY0jnUi3GiMPtw5nnq3xtg3tf4d+VO2miZNE6pVqqvIjSQsa\n" +
            "x3uBqonhkP1qh3YhCuVINvmJuci5K72QOMjUPEzH0qlvpmYUdw7H5Lu/ZwKBgQCa\n" +
            "XWL1UMVw9w+R4VOmdY4SwO7W6az8l2vAMg9Ndq8PrukBe1v0f5tTbl+A7f6C46gD\n" +
            "AZWVd0RGuwTDbZ9lr6utOTk8Q+pJ5XaPX+Czl6UVzUbr8dx2/ggj/Vc/+Tf4vt+z\n" +
            "JwhOyXP2twCmzWPpTGtePCm/9RBD3bBWMrJhwW3wSQKBgGnTL6raihSylx7nnBEE\n" +
            "C1HzTpiaU6EKRGuUaCL2fRLjJl//ufVMwLlgCc+8+RDXxHZiVn/aQ6SHARP0TDHR\n" +
            "Yzl6k8A5iRUqoITGc02+c8+R5i6HOlJ33I+eP8LkC8nTK3vf/Wyx8z15I6IKZggF\n" +
            "0IAWwzQLwFGgIrFxm0fPCuFC";
    /**
     * 公钥
     */
    // private final static String PUBLICKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyi1NK4NnbYgL17c5U7HC\n" +
    //         "sF9gf741+xPyyD84SYuw2TYbpdOBNfiYRXFyUlwnHMDR+w2dQ+l94mjJHwsPl1BU\n" +
    //         "3Z3d/P7JoU4tSi8fjk21kXnxJHuvoQk8bi/o/tN6E0zERiA3vqOBncOhegXQjf6g\n" +
    //         "wTszD991BtFj4srSY95J1myyrPDH19HkNVKCJvyA0wIgrdpq/1ujbkdBbun7X3Jr\n" +
    //         "S5GN94Agn6wQp5HX5DBrtindyARl778zNXGVsYcsvVGJySAqPImtqKINYU+g4tNu\n" +
    //         "UqP2yr49qBD9nL+7i81sa++HTndxSF7Eyme4JaLD53ofuxhSGc14Ob8Dp/GWJ46U\n" +
    //         "3wIDAQAB";
    private final static String PUBLICKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAihTj8ksHMQmyrwUVTR213j3iyoL3HJ1X48laOKZNSThyC+6crGupyNXeuz1RuNCZZ1NWiWXOLZKKPFnzyx1swgUQho5wkrA5QqN0fT0GBt35L59OZXHbfdY7coDJCce1rhhZzEML1KDIF7MxO+yA0FiOLj93iRrXj37jICLLLNS1p0XPTF2FJKHrEgABFmyBpbPODT109Tx5U5eGrwUk/1I+WKT6w2VTpMkXEm0794OgbE9j/SqTqWvxKjc19rh949qOxApPPmscmFM1uIdvB1K5fOAsE5hR9Noqwmobq6lJWK4tT2O1NA8J/T74htBspRat1dp5XlRb5IMKejVHDwIDAQAB";

    /**
     * 加解密对象
     */

    private final static RSA RSA = new RSA(PRIVATEKEY, PUBLICKEY);

    // private static final RSA RSA;
    // static {
    //     File pubFile = new File(RSAUtil.class.getResource("/public_key.txt").getPath());
    //     String publicKey = FileUtil.readString(pubFile, StandardCharsets.UTF_8);
    //     System.out.println("publicKey:" + publicKey);
    //     File priFile = new File(RSAUtil.class.getResource("/private_key.txt").getPath());
    //     String privateKey = FileUtil.readString(priFile, StandardCharsets.UTF_8);
    //     System.out.println("privateKey:" + privateKey);
    //     RSA = new RSA(privateKey, publicKey);
    // }

    /**
     * 加密 返回字节,指定编码
     *
     * @return
     */
    public static String encrypt(String message) {
        return Base64.getEncoder().encodeToString(RSA.encrypt(message, StandardCharsets.UTF_8, KeyType.PublicKey));
        // String s = RSA.encryptBcd(message, KeyType.PublicKey, StandardCharsets.UTF_8);
        // String s2 = Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
        // return s2;
    }

    /**
     * 解密 返回字节
     *
     * @return
     */
    public static String decrypt(String data) {
        // return new String(RSA.decrypt(Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8)), KeyType.PrivateKey));
        // String s3 = new String(Base64.getDecoder().decode(data), StandardCharsets.UTF_8);
        // String s1 = RSA.decryptFromBcdToStr(s3, KeyType.PrivateKey, StandardCharsets.UTF_8);
        // return s1;
        // Base64.getDecoder()  和 Base64.getMimeDecoder() 编码不一致
        byte[] decrypt = RSA.decrypt(Base64.getMimeDecoder().decode(data), KeyType.PrivateKey);
        return new String(decrypt, StandardCharsets.UTF_8);
    }

}
