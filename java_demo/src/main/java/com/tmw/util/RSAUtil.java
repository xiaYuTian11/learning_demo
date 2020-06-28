package com.tmw.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

import java.nio.charset.Charset;

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
    // private final static String PRIVATEKEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMGomqgbOMkgDx7yFRTp6sEgAFckcOZB/AkmgEov0EcJQQiBh/28HjQv7kBXGqv1yaYj9LwuwIyq1WbZQjPZ7hc8tfannLGZ0eqS7/me12EXTXs6S4fqYgniWcZr9kHxCPO3rPzx9jaOSSNlwMFF/YNJx/DZGa+rQ2KLOSmFSuhRAgMBAAECgYAoumPkTO1RZzum33TNlDB581f++7v+wQvXhNBgSdP8zJZvyoYmN+UGRrpNr8P9PFDvbwpudUqritYcrj59Fy06OYyYLxMjJypRrpw/Xz6/a0UKD+mke4rkv8Dm5p29JmDq4SkxbHMmVpJ0ACthYtwR/HZ8A8gNJJ1KVN777mU+EQJBAPqK9MnhVpHYtcjP301zGKmD6MMj0EOGW/EY2/r/6NLlsEaucmUycE/jySAdvFFojAKekllQsTm0DGzDILr4Hf0CQQDF4HUyH9WmAeuJXEzY+hEURc4IKi4qoYBjTU+QnlBGRiiQXSAvejoS60H3V4ti/lY8I4Jz5ZSBhnDOZykZ8fnlAkEArWpU+Q9Bzn2DhdVsKL2LynmFONynILX1GbItQVS0oJVfJ+1DPQxmdMWjxtkOKgMFNiLwBWY25UvcdcOx+fgXOQJAMXG81yGNSUYBjPk7Wpxh2Cb5LKEdmBly811GHGTyuhyd1jTu7e4kxMV0XlHJypqzAaP93OfpC9SEK48rfggw4QJAI5ftnoOulZN3VvaIHb/s0FQtQn+iS5MTD2Rh8bmfaVXUMvSCe751ua/Au4npPykIwUrgW3N9UxnWqJ1WaT+Eug==";
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
    // private final static String PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBqJqoGzjJIA8e8hUU6erBIABXJHDmQfwJJoBKL9BHCUEIgYf9vB40L+5AVxqr9cmmI/S8LsCMqtVm2UIz2e4XPLX2p5yxmdHqku/5ntdhF017OkuH6mIJ4lnGa/ZB8Qjzt6z88fY2jkkjZcDBRf2DScfw2Rmvq0NiizkphUroUQIDAQAB";
    private final static String PUBLICKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwSuf1LuxJIfgYLaJehMj\n" +
            "Z03eD5X4fqVxTWxTD4b6LKA09/VkIL/UH05wpVCynYUhGVliW4ivSipA/UJ+XJVv\n" +
            "5SPZYx6YApwH7f9t5H11VnWmJm4CQloTRXpJ3afzYYEdZ5yMpuRspuRPy8q223bc\n" +
            "nZuvkXiZKoX3uJ2pG57GDMHRJCGwEfL0Qe/MktNPBAqdKeNC8smK19sGI2IuwnKD\n" +
            "32r5aEPs15JHuj7R0fgKL5rz5LdkDFUdfXOwCWRL5Jy5BpYiiPalKPgs/Vl5o302\n" +
            "3P81mRsM3eeuI88nMksAbqSJUYxBhSK4jMEtZ2hVYo54/KC/vhdyRdQnm8uEr2L2\n" +
            "pQIDAQAB+5AVxqr9cmmI/S8LsCMqtVm2UIz2e4XPLX2p5yxmdHqku/5ntdhF017OkuH6mIJ4lnGa/ZB8Qjzt6z88fY2jkkjZcDBRf2DScfw2Rmvq0NiizkphUroUQIDAQAB";

    /**
     * 加解密对象
     */
    private final static RSA RSA = new RSA(PRIVATEKEY, PUBLICKEY);

    /**
     * 加密 返回字节
     *
     * @return
     */
    public static byte[] encrypt(String message) {
        return encrypt(message, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 加密 返回字节,指定编码
     *
     * @return
     */
    public static byte[] encrypt(String message, Charset charset) {
        return RSA.encrypt(message, charset, KeyType.PrivateKey);
    }

    /**
     * 加密 返回字符
     *
     * @return
     */
    public static String encryptStr(String message) {
        return encryptStr(message, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 加密 返回字节,指定编码
     *
     * @return
     */
    public static String encryptStr(String message, Charset charset) {
        return RSA.encryptBcd(message, KeyType.PrivateKey, charset);
    }

    /**
     * 解密 返回字节
     *
     * @return
     */
    public static byte[] decrypt(byte[] message) {
        return RSA.decrypt(message, KeyType.PublicKey);
    }

    /**
     * 解密 返回字节
     *
     * @return
     */
    public static byte[] decrypt(String message) {
        return RSA.decrypt(message.getBytes(CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
    }

    /**
     * 解密 返回字符
     *
     * @return
     */
    public static String decryptStr(String message) {
        return decryptStr(message, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 解密 返回字符
     *
     * @return
     */
    public static String decryptStr(String message, Charset charset) {
        return RSA.decryptFromBcdToStr(message, KeyType.PublicKey, charset);
    }


}
