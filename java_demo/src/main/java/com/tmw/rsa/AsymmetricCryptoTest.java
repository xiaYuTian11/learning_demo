package com.tmw.rsa;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.tmw.util.RSAUtil;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;

/**
 * @author TMW
 * @date 2020/6/23 10:04
 */
public class AsymmetricCryptoTest {

    @Test
    public void test01() throws UnsupportedEncodingException {
        RSA rsa = new RSA();
        //获得私钥
        // rsa.getPrivateKey();
        // rsa.getPrivateKeyBase64();
        // //获得公钥
        // rsa.getPublicKey();
        // rsa.getPublicKeyBase64();

        //公钥加密，私钥解密
        byte[] encrypt = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);
        System.out.println(new String(encrypt, "utf-8"));
        System.out.println(new String(decrypt, "utf-8"));
        //Junit单元测试
        //Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));

        //私钥加密，公钥解密
        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);

        //Junit单元测试
        // Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt2, CharsetUtil.CHARSET_UTF_8));

    }

    @Test
    public void test02() {
        // KeyPair pair = SecureUtil.generateKeyPair("RSA");
        // System.out.println(pair.getPrivate());
        // System.out.println(pair.getPublic());

        // String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMGomqgbOMkgDx7yFRTp6sEgAFckcOZB/AkmgEov0EcJQQiBh/28HjQv7kBXGqv1yaYj9LwuwIyq1WbZQjPZ7hc8tfannLGZ0eqS7/me12EXTXs6S4fqYgniWcZr9kHxCPO3rPzx9jaOSSNlwMFF/YNJx/DZGa+rQ2KLOSmFSuhRAgMBAAECgYAoumPkTO1RZzum33TNlDB581f++7v+wQvXhNBgSdP8zJZvyoYmN+UGRrpNr8P9PFDvbwpudUqritYcrj59Fy06OYyYLxMjJypRrpw/Xz6/a0UKD+mke4rkv8Dm5p29JmDq4SkxbHMmVpJ0ACthYtwR/HZ8A8gNJJ1KVN777mU+EQJBAPqK9MnhVpHYtcjP301zGKmD6MMj0EOGW/EY2/r/6NLlsEaucmUycE/jySAdvFFojAKekllQsTm0DGzDILr4Hf0CQQDF4HUyH9WmAeuJXEzY+hEURc4IKi4qoYBjTU+QnlBGRiiQXSAvejoS60H3V4ti/lY8I4Jz5ZSBhnDOZykZ8fnlAkEArWpU+Q9Bzn2DhdVsKL2LynmFONynILX1GbItQVS0oJVfJ+1DPQxmdMWjxtkOKgMFNiLwBWY25UvcdcOx+fgXOQJAMXG81yGNSUYBjPk7Wpxh2Cb5LKEdmBly811GHGTyuhyd1jTu7e4kxMV0XlHJypqzAaP93OfpC9SEK48rfggw4QJAI5ftnoOulZN3VvaIHb/s0FQtQn+iS5MTD2Rh8bmfaVXUMvSCe751ua/Au4npPykIwUrgW3N9UxnWqJ1WaT+Eug==";
        // String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBqJqoGzjJIA8e8hUU6erBIABXJHDmQfwJJoBKL9BHCUEIgYf9vB40L+5AVxqr9cmmI/S8LsCMqtVm2UIz2e4XPLX2p5yxmdHqku/5ntdhF017OkuH6mIJ4lnGa/ZB8Qjzt6z88fY2jkkjZcDBRf2DScfw2Rmvq0NiizkphUroUQIDAQAB";
        String messgae = "tmwhahaha";
        // RSA rsa = new RSA(privateKey, publicKey);
        String encrypt = RSAUtil.encryptStr(messgae);
        System.out.println(encrypt);
        String decrypt = RSAUtil.decryptStr(encrypt);
        System.out.println(new String(decrypt));

    }
}
