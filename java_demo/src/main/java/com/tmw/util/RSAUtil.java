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
    private final static String PRIVATEKEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMGomqgbOMkgDx7yFRTp6sEgAFckcOZB/AkmgEov0EcJQQiBh/28HjQv7kBXGqv1yaYj9LwuwIyq1WbZQjPZ7hc8tfannLGZ0eqS7/me12EXTXs6S4fqYgniWcZr9kHxCPO3rPzx9jaOSSNlwMFF/YNJx/DZGa+rQ2KLOSmFSuhRAgMBAAECgYAoumPkTO1RZzum33TNlDB581f++7v+wQvXhNBgSdP8zJZvyoYmN+UGRrpNr8P9PFDvbwpudUqritYcrj59Fy06OYyYLxMjJypRrpw/Xz6/a0UKD+mke4rkv8Dm5p29JmDq4SkxbHMmVpJ0ACthYtwR/HZ8A8gNJJ1KVN777mU+EQJBAPqK9MnhVpHYtcjP301zGKmD6MMj0EOGW/EY2/r/6NLlsEaucmUycE/jySAdvFFojAKekllQsTm0DGzDILr4Hf0CQQDF4HUyH9WmAeuJXEzY+hEURc4IKi4qoYBjTU+QnlBGRiiQXSAvejoS60H3V4ti/lY8I4Jz5ZSBhnDOZykZ8fnlAkEArWpU+Q9Bzn2DhdVsKL2LynmFONynILX1GbItQVS0oJVfJ+1DPQxmdMWjxtkOKgMFNiLwBWY25UvcdcOx+fgXOQJAMXG81yGNSUYBjPk7Wpxh2Cb5LKEdmBly811GHGTyuhyd1jTu7e4kxMV0XlHJypqzAaP93OfpC9SEK48rfggw4QJAI5ftnoOulZN3VvaIHb/s0FQtQn+iS5MTD2Rh8bmfaVXUMvSCe751ua/Au4npPykIwUrgW3N9UxnWqJ1WaT+Eug==";
    /**
     * 公钥
     */
    private final static String PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBqJqoGzjJIA8e8hUU6erBIABXJHDmQfwJJoBKL9BHCUEIgYf9vB40L+5AVxqr9cmmI/S8LsCMqtVm2UIz2e4XPLX2p5yxmdHqku/5ntdhF017OkuH6mIJ4lnGa/ZB8Qjzt6z88fY2jkkjZcDBRf2DScfw2Rmvq0NiizkphUroUQIDAQAB";

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
