package com.tmw.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @author lirb
 * @Create 2010-06-07 该类用于加密时使用
 */
public final class UnifyEncript {
    // 生成密钥的参数
    private final String keyStr = "sinosoftUnifiedUser";

    Key key;

    /**
     * 生成密钥的方法
     *
     * @param strKey 要加密的字符
     * @author li
     * @date 2013-6-9
     */
    public void setKey(String strKey) {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance("DES");
            // 防止linux下 随机生成key
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(strKey.getBytes());
            _generator.init(56, secureRandom);
            this.key = _generator.generateKey();
            _generator = null;
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error initializing SqlMap class. Cause: " + e);
        }
    }

    public UnifyEncript() {
        super();
        this.setKey(this.keyStr);
    }

    /**
     * 加密方法，返回加密的字符串
     *
     * @param strMing
     * @return String
     * @author zl
     * @date 2013-6-9
     */
    public String encript(String strMing) {
        byte[] byteMi = null;
        byte[] byteMing = null;
        String strMi = "";
        BASE64Encoder base64en = new BASE64Encoder();
        try {
            byteMing = strMing.getBytes("UTF8");
            byteMi = this.encryptByte(byteMing);
            strMi = base64en.encode(byteMi);
            strMi = strMi.replaceAll(" ", "%20");
            strMi = strMi.replaceAll("\\+", "%2B");
            strMi = strMi.replaceAll("/", "%2F");
            strMi = strMi.replaceAll("=", "%3D");
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error initializing SqlMap class. Cause: " + e);
        } finally {
            base64en = null;
            byteMing = null;
            byteMi = null;
        }

        strMi = strMi.replaceAll(" ", "%20");
        strMi = strMi.replaceAll("\\+", "%2B");
        strMi = strMi.replaceAll("/", "%2F");
        strMi = strMi.replaceAll("=", "%3D");
        return strMi.replaceAll("\r\n", "");
    }

    /**
     * 加密以 byte[] 明文输入 ,byte[] 密文输出
     *
     * @param byteS
     * @return
     * @author zl
     * @date 2013-6-9
     */
    private byte[] encryptByte(byte[] byteS) {
        byte[] byteFina = null;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byteFina = cipher.doFinal(byteS);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error initializing SqlMap class. Cause: " + e);
        } finally {
            cipher = null;
        }
        return byteFina;
    }

    /**
     * 解密以 byte[] 密文输入 , 以 byte[] 明文输出
     *
     * @param byteD
     * @return byte[]
     * @author zl
     * @date 2013-6-9
     */
    private byte[] decryptByte(byte[] byteD) {
        Cipher cipher;
        byte[] byteFina = null;
        try {
            cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byteFina = cipher.doFinal(byteD);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error initializing SqlMap class. Cause: " + e);
        } finally {
            cipher = null;
        }
        return byteFina;
    }

    /**
     * 解密 以 String 密文输入 ,String 明文输出
     *
     * @param strMi
     * @return String
     * @author zl
     * @date 2013-6-9
     */
    public String descript(String strMi) {

        BASE64Decoder base64De = new BASE64Decoder();
        byte[] byteMing = null;
        byte[] byteMi = null;
        String strMing = "";
        try {
            strMi = strMi.replaceAll("%20", " ");
            strMi = strMi.replaceAll("%2B", "+");
            strMi = strMi.replaceAll("%2F", "/");
            strMi = strMi.replaceAll("%3D", "=");
            strMi = strMi.replaceAll("%25", "%");
            byteMi = base64De.decodeBuffer(strMi);
            byteMing = this.decryptByte(byteMi);
            strMing = new String(byteMing, "UTF8");
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error initializing SqlMap class. Cause: " + e);
        } finally {
            base64De = null;
            byteMing = null;
            byteMi = null;
        }
        return strMing;
    }

    /**
     * 测试方法
     *
     * @param args
     * @return void
     * @throws Exception
     * @author zl
     * @date 2013-6-9
     */
    public static void main(String[] args) throws Exception {

        UnifyEncript des = new UnifyEncript();

        String str1 = "{\"queryCode\":\"00fd015af5bee55e0001\",\"userInfos\":\"接访人员\"}";

        String str = "%252FDaZ5xYVTCoZ316IJBGKSn%252Fi7PGdLcFrshK6KUfHDp4PTN85DihdTnpOJDfT7EwhDMB1NXGP8tYbh00iOP7p2Vc8GAmo8UCG0K8WrMtvFtNSZnAWBJQnVDeSdvKwZmD7WlmcZ58ys7mxuuETg%252Bjvz3oE9Gaua%252B5lB0xrgT5ba7kzzMS07nbJNd5HWuTGuTgkJzrZch1uLE5uEECWSvBNoULo67KsMa7w%252F3h0ibHjm4F7MBPCZaIHAufA8QhMuqHZXABoWe49D9ti7%252Fl%252FQo3yUjRxKmv5SVLTH0toa%252FTCPv4dxTCz00OMHjhjCddAkbLPOhtevzN0SQEJWgKqrtQqArDu5p3kj%252BHiW78ZN4Hfv9QzYzP6UEyabPTU%252Bh5JQcYZvDT0LOT9fYnmH8lqMB3cCsNDlOXialnJFt48RtWXurLT1uXTvy8hFJR2%252F3maYqQdkq70Yamx1%252BNauGPQXHM6ZaN%252BWzHXVAK4R8gecLyMs3e5lZBdt2QLgAv8TxNFn71G11QxuQb7sdUG%252FZQMFeVUzaGZApN8uQvfLTASMM13j2tQIz95HsVTl2kJunDaT%252F8NMrLMKEPCl1aY5IoY2vnSkFSy30cZsfgsQXKvzAzbjz5OorNfu8F%252BRmkJunDaT%252F8NMtsWbZ10hb7vYAHGqFeBF4fuxkv7SJKdd3JrmB0XVzXN5VX5wJnJfwt82sTIM9YjRYHzkGP1MQ%252Bujhto0ArcH7M32SJSFuHrAnM6M8iuEQkK784SqF4s%252BENYaBr%252FP%252FGTkwDzQ2Lg33v9w0qQMgt4GAi3Z5dwhO%252Feh9tIldjhwUZGKZuFnjfJMiljU9NpHWlHWSrKkwkYWicFsPvYN%252B53ddOVOU05VIXc";
        // String str = "%2FDaZ5xYVTCoZ316IJBGKSn%2Fi7PGdLcFrshK6KUfHDp4PTN85DihdTnpOJDfT7EwhDMB1NXGP8tYbh00iOP7p2Vc8GAmo8UCG0K8WrMtvFtNSZnAWBJQnVDeSdvKwZmD7WlmcZ58ys7mxuuETg%2Bjvz3oE9Gaua%2B5lB0xrgT5ba7kzzMS07nbJNd5HWuTGuTgkJzrZch1uLE5uEECWSvBNoULo67KsMa7w%2F3h0ibHjm4F7MBPCZaIHAufA8QhMuqHZXABoWe49D9ti7%2Fl%2FQo3yUjRxKmv5SVLTH0toa%2FTCPv4dxTCz00OMHjhjCddAkbLPOhtevzN0SQEJWgKqrtQqArDu5p3kj%2BHiW78ZN4Hfv9QzYzP6UEyabPTU%2Bh5JQcYZvDT0LOT9fYnmH8lqMB3cCsNDlOXialnJFt48RtWXurLT1uXTvy8hFJR2%2F3maYqQdkq70Yamx1%2BNauGPQXHM6ZaN%2BWzHXVAK4R8gecLyMs3e5lZBdt2QLgAv8TxNFn71G11QxuQb7sdUG%2FZQMFeVUzaGZApN8uQvfLTASMM13j2tQIz95HsVTl2kJunDaT%2F8NMrLMKEPCl1aY5IoY2vnSkFSy30cZsfgsQXKvzAzbjz5OorNfu8F%2BRmkJunDaT%2F8NMtsWbZ10hb7vYAHGqFeBF4fuxkv7SJKdd3JrmB0XVzXN5VX5wJnJfwt82sTIM9YjRYHzkGP1MQ%2Bujhto0ArcH7M32SJSFuHrAnM6M8iuEQkK784SqF4s%2BENYaBr%2FP%2FGTkwDzQ2Lg33v9w0qQMgt4GAi3Z5dwhO%2Feh9tIldjhwUbLks2gm0yF6cFKDV%2BPDcGpT5mypAEhnDw6o7xm7jrLuNfNEXppMlaj";
        String descript = des.descript(str);
        System.out.println(descript);

        // DES 加密字符串
        String str2 = des.encript(str1);
        String str5 = des.descript(str2);
        System.out.println("jiemihou:" + str5);
//		String str3 = "c9J%2FbQXkRms%2Fp3gaKTyYV%2FQn28cpv%2B%Bv";
        //String str3 = vjWVcvqzB%2FuEgUJajYCQtJEDJse1Kp%2BocVHgc6%2B7YwdN975kLqdFaY3lINZVrNo4ftHbWx3yykEtnemT29wQTVlXIrzXPw4rTegIqKpm6suscEPNJ%2F3rQ0CGnb9YV3ojVkjNDaxu89oGp0npsfEcWMVX2nORH2WKJ2pF0pNu4jwSFFjWVazaOH7R21sd8spBLZ3pk9vcEE1ZVyK81z8OK03oCKiqZurLrHBC%2Ffkb1LYaGKyGJuDrRmcgYNsVJQxyZGfMN1XrFJin6e9M3PWpfMPRkh5Ibe1rP1uAlK3D6y7yMWkHyiWSPaq9my48HMKpHrCU91X0d%2BKV7ZtlPNlD90bsc0s7TZfuA3dn2F6iMa4piAKRaIQ0DrUpoXis2yTrnt4fWx3yykEtnevycJqOqhzIzQmkDwTxUe77TNz1qXzD0ZMIJHaIPI0%2FEBgIV%2Bbrhe7%2Frbo6ykswpKw3gkeBrumm7E0EvwTFf3ZPbjt2TargwA2leKeRPRW1NA0Dm6yUbRbs2Xi9X0ZMF6l0mfD5gc%2FCh6IhuY8DiVODp4Yv9VJ8nc9UOy9H1vpti2f4psuT9dcYVIlgleHyl2hNBL8ExX92T247dk2q4MANpXinkT0VtTbFdbPG6rQoWpbMzjrnCbSNDq3MLa4Ew3m7Db%2FLsVRVtEJwDEsk%2FUGo3Ethqj5pr2OpINvlwFiL1lU6UlCmn6p2m9fvofXIK21zhbT2zAs7Lfp3k4sihvGR007vVumtzcsu%2FnKZZQVl38kn1S96Zy0%2FoiG5jwOJU4Onhi%2F1Unydz1Q7L0fW%2Bm2IMWshjet6P6Zvklu%2FomYts";
        // DES 解密字符串
//		String deStr = des.descript(str3);
//		System.out.println(" 加密前： " + str1);
//		System.out.println(" 加密后： " + str2);
        //System.out.println(" 加密后长度： " + str2.length());
//		System.out.println(" 解密后： " + deStr);

        //System.out.println(stra.length);
    }
}
