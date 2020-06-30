package com.tmw.rsa;

import cn.hutool.http.HttpUtil;
import com.tmw.util.RSAUtil;
import org.junit.jupiter.api.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author TMW
 * @date 2020/6/29 14:56
 */
public class RSATest {

    @Test
    public void test01() throws UnsupportedEncodingException {
        String reqXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<APPROOT>" +
                "<PUBLIC>" +
                "<TRX_CODE>FRZ01</TRX_CODE>" +
                "</PUBLIC>" +
                "<PRIVATE>" +
                "<ACCSEQ>09169001</ACCSEQ>" +
                "<BUSTYPE>2</BUSTYPE>" +
                "<REMARK1></REMARK1>" +
                "<REMARK2></REMARK2>" +
                "<REMARK3></REMARK3>" +
                "</PRIVATE>" +
                "</APPROOT>";
        String encrypt = RSAUtil.encrypt(reqXml);
        String encode = URLEncoder.encode(encrypt, StandardCharsets.UTF_8.toString());
        String resData = HttpUtil.get("http://cq-test.dccnet.com.cn/cqfh/cqjwjc?endata=" + encode);
        System.out.println("返回字符串：" + resData);
        System.out.println("解密后字符串：" + RSAUtil.decrypt(resData));
    }

    @Test
    public void test02() {
        String str = "0123456789";
        String encrypt = enCodeXmlData(null, str);
        System.out.println("加密：" + encrypt);
        String decrypt = deCodeXmlData(null, encrypt);
        System.out.println("解密：" + decrypt);
    }

    /**
     * 工行 公钥加密
     *
     * @param bizCode
     * @param paramStr
     * @return
     */
    public static String enCodeXmlData(String bizCode, String paramStr) {
        String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwSuf1LuxJIfgYLaJehMj\n" +
                "Z03eD5X4fqVxTWxTD4b6LKA09/VkIL/UH05wpVCynYUhGVliW4ivSipA/UJ+XJVv\n" +
                "5SPZYx6YApwH7f9t5H11VnWmJm4CQloTRXpJ3afzYYEdZ5yMpuRspuRPy8q223bc\n" +
                "nZuvkXiZKoX3uJ2pG57GDMHRJCGwEfL0Qe/MktNPBAqdKeNC8smK19sGI2IuwnKD\n" +
                "32r5aEPs15JHuj7R0fgKL5rz5LdkDFUdfXOwCWRL5Jy5BpYiiPalKPgs/Vl5o302\n" +
                "3P81mRsM3eeuI88nMksAbqSJUYxBhSK4jMEtZ2hVYo54/KC/vhdyRdQnm8uEr2L2\n" +
                "pQIDAQAB";
        String encryptStr = null;
        RSATest.CorpPaymentPubRSACrypt rSACrypt = new RSATest.CorpPaymentPubRSACrypt();
        BASE64Decoder base64decoder = new BASE64Decoder();
        BASE64Encoder base64encoder = new BASE64Encoder();

        try {
            System.out.println("合作方" + bizCode + ":RSA加密,注入密钥，加密得到密文");
            rSACrypt.setKeyBytes(base64decoder.decodeBuffer(pubKey));
            encryptStr = base64encoder.encode(rSACrypt.encrypt(paramStr.getBytes(StandardCharsets.UTF_8)));

        } catch (Exception e) {
            System.out.println("合作方" + bizCode + ":RSA加密,xml报文加密异常，加密失败");
            e.printStackTrace();
        }
        return encryptStr;
    }

    /**
     * 本地测试 秘钥解密
     *
     * @param bizCode
     * @param decryptStr
     * @return
     */
    public static String deCodeXmlData(String bizCode, String decryptStr) {
        String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDBK5/Uu7Ekh+Bgtol6EyNnTd4Plfh+pXFNbFMPhvosoDT39WQgv9QfTnClULKdhSEZWWJbiK9KKkD9Qn5clW/lI9ljHpgCnAft/23kfXVWdaYmbgJCWhNFekndp/NhgR1nnIym5Gym5E/Lyrbbdtydm6+ReJkqhfe4nakbnsYMwdEkIbAR8vRB78yS008ECp0p40LyyYrX2wYjYi7CcoPfavloQ+zXkke6PtHR+AovmvPkt2QMVR19c7AJZEvknLkGliKI9qUo+Cz9WXmjfTbc/zWZGwzd564jzycySwBupIlRjEGFIriMwS1naFVijnj8oL++F3JF1Ceby4SvYvalAgMBAAECggEBAI96fZ8fQjYmDo0HSBm6OWOzvKrK8+XtsLiXKyKvWQbogC9kv1hr43zVYGoKBIFnruwLLjlazwhBVkBGsWDHRnNmK0OqJ7JYfrNSNCew4AS2IhENSPRLv2CtskvHQck8g8C/0ODtbZ6Qmox+J0/fe4I9byyX7PxewV8JC+COEADMcNnjGgOv/+UcUiP5wSuAn5vVlzsa0AQT6Wv2+5G3WPuTXZHxZOFYrRya7PdpddoPg2ZODt65ULmsxgpHJ5XeD1NRLPiJOwRbn5prGUcr6kgE+mFoOijqnq5SyHx9SxJUZ+M7ccIufiw3GsKMXwLzL1B+dLa+Pc7rbfmZKDOXnskCgYEA+C6OBVJVU7c3L6c0tswEqeUEs4DBeDzgd4yf3adXHzodY4UhF/TjQ2/x+tpi/VWG56kV5rvHC7i/XY5qE2XX/tazB3/h4w5WH4nK3OxxKYWaeK6d6IFhKkOZQMhlI1XZb1IAWN8bSWNlJODHd/AWhXvDSB42JL6zPbCM5CnNX88CgYEAx0FvAFyWd2u5Id4as7+lCosM4w+HgQpty8CWX+lkxjTtBfjER7J4lUvowyODmsO7DpcGgnmVyDXJfyJ7fSQ1of+rZnZXj56MMjZBAC7qYvWEFx3+JhdFKxZsdAMbDlWEYHCZOuGKA7nZtXzBpvo8MkxRYYJZy5d3mWXA5Q3GC0sCgYBGHzlHbaToO2sfTroJMAndSvUSFr4TT/gILzZ4NTDbhNsK5HGeSDXyNu4C97NiasjsKY0jnUi3GiMPtw5nnq3xtg3tf4d+VO2miZNE6pVqqvIjSQsax3uBqonhkP1qh3YhCuVINvmJuci5K72QOMjUPEzH0qlvpmYUdw7H5Lu/ZwKBgQCaXWL1UMVw9w+R4VOmdY4SwO7W6az8l2vAMg9Ndq8PrukBe1v0f5tTbl+A7f6C46gDAZWVd0RGuwTDbZ9lr6utOTk8Q+pJ5XaPX+Czl6UVzUbr8dx2/ggj/Vc/+Tf4vt+zJwhOyXP2twCmzWPpTGtePCm/9RBD3bBWMrJhwW3wSQKBgGnTL6raihSylx7nnBEEC1HzTpiaU6EKRGuUaCL2fRLjJl//ufVMwLlgCc+8+RDXxHZiVn/aQ6SHARP0TDHRYzl6k8A5iRUqoITGc02+c8+R5i6HOlJ33I+eP8LkC8nTK3vf/Wyx8z15I6IKZggF0IAWwzQLwFGgIrFxm0fPCuFC";
        String decryptXml = null;
        RSATest.CorpPaymentPubRSACrypt rSACrypt1 = new RSATest.CorpPaymentPubRSACrypt();
        BASE64Decoder base64decoder = new BASE64Decoder();
        try {
            System.out.println("合作方" + bizCode + ":RSA解密,注入密钥，解密得到明文");
            rSACrypt1.setKeyBytes(base64decoder.decodeBuffer(privateKey));
            decryptXml = new String(rSACrypt1.decrypt(base64decoder.decodeBuffer(decryptStr)), "UTF-8");

        } catch (Exception e) {
            System.out.println("合作方" + bizCode + ":RSA加密,xml报文解密异常，解密失败");
            e.printStackTrace();
        }
        return decryptXml;
    }

    static class CorpPaymentPubRSACrypt {
        public static final String KEY_ALGORITHM = "RSA";

        /**
         * 证书内容
         */
        private byte[] keyBytes;

        /**
         * 1024位的证书，加密时最大支持117个字节，解密时为128；
         * 2048位的证书，加密时最大支持245个字节，解密时为256。
         * RSA最大加密明文大小
         */
        protected static final int MAX_ENCRYPT_BLOCK = 245;

        /**
         * 1024位的证书，加密时最大支持117个字节，解密时为128；
         * 2048位的证书，加密时最大支持245个字节，解密时为256。
         * RSA最大解密密文大小
         */
        protected static final int MAX_DECRYPT_BLOCK = 256;

        public byte[] getKeyBytes() {
            return keyBytes;
        }

        public void setKeyBytes(byte[] keyBytes) {
            this.keyBytes = keyBytes;
        }

        /**
         * 提取公钥
         */
        protected PublicKey toPublicKey(byte[] key) {
            // 构造X509EncodedKeySpec对象
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
            // KEY_ALGORITHM 指定的加密算法
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                return keyFactory.generatePublic(keySpec);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 提取私钥
         **/
        protected PrivateKey toPrivateKey(byte[] key) {
            // 构造PKCS8EncodedKeySpec对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
            // KEY_ALGORITHM 指定的加密算法
            KeyFactory keyFactory = null;
            try {
                keyFactory = KeyFactory.getInstance("RSA");
                // 取私钥匙对象
                PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
                return priKey;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 私钥解密
         *
         * @param encryptedData 已加密数据
         * @return
         */
        public byte[] decrypt(byte[] encryptedData) throws Exception {

            try {
                Cipher cipher = getCipher();
                cipher.init(Cipher.DECRYPT_MODE, toPrivateKey(getKeyBytes()));
                return doFinal(cipher, encryptedData, MAX_DECRYPT_BLOCK);
            } catch (Exception e) {
                throw e;
            }

        }

        /**
         * <p>
         * 公钥加密
         * </p>
         *
         * @param data 源数据
         */
        public byte[] encrypt(byte[] data) throws Exception {
            try {
                Cipher cipher = getCipher();
                cipher.init(Cipher.ENCRYPT_MODE, toPublicKey(getKeyBytes()));
                return doFinal(cipher, data, MAX_ENCRYPT_BLOCK);
            } catch (Exception e) {
                throw e;
            }
        }

        protected Cipher getCipher() throws Exception {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            return cipher;
        }

        protected byte[] doFinal(Cipher cipher, byte[] data, int maxBlock) throws Exception {
            int inputLen = data.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > maxBlock) {
                    cache = cipher.doFinal(data, offSet, maxBlock);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * maxBlock;
            }
            byte[] encryptedData = out.toByteArray();
            out.close();
            return encryptedData;
        }

    }
}
