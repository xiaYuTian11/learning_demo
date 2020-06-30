package com.tmw.rsa;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.tmw.util.RSAUtil;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.Base64;

/**
 * @author TMW
 * @date 2020/6/23 10:04
 */
public class AsymmetricCryptoTest {
    /**
     * 私钥
     */
    private final static String PRIVATEKEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDKLU0rg2dtiAvX\n" +
            "tzlTscKwX2B/vjX7E/LIPzhJi7DZNhul04E1+JhFcXJSXCccwNH7DZ1D6X3iaMkf\n" +
            "Cw+XUFTdnd38/smhTi1KLx+OTbWRefEke6+hCTxuL+j+03oTTMRGIDe+o4Gdw6F6\n" +
            "BdCN/qDBOzMP33UG0WPiytJj3knWbLKs8MfX0eQ1UoIm/IDTAiCt2mr/W6NuR0Fu\n" +
            "6ftfcmtLkY33gCCfrBCnkdfkMGu2Kd3IBGXvvzM1cZWxhyy9UYnJICo8ia2oog1h\n" +
            "T6Di025So/bKvj2oEP2cv7uLzWxr74dOd3FIXsTKZ7glosPneh+7GFIZzXg5vwOn\n" +
            "8ZYnjpTfAgMBAAECggEBAJOfgmKMXz/1/5eyPJSlDmBUNBTyYV4EkdiveL0S172y\n" +
            "qXtqtUUgUKccD/qdOR3gmTI0HanR2s4v7vLQ08CLsnvjYozxyW4TtDQftm3kxfgE\n" +
            "TxRxj6sJIGvgZcNzzQY8nnAJ5Eo5/1Gf389GYaLc0qplPIKRilTBHasip36ud8Gt\n" +
            "bi9q6qoqDOP+bt7I4ZBMPEXZpaOfBBFnj6rYdNy8YGECZBdbnoBm3Xjs8HMv03/I\n" +
            "P5ENrKG0D38SRSnzrjivdFTzXWze/a9gJkts508jBGrMXt0QJ1QUaQi4uhSNJMd2\n" +
            "+OnwEcDaMamjal86QdjXgKLPXQ0c3gFsrEw9qKqtj+ECgYEA7jgmz1pBFSrnhAA0\n" +
            "f7EWs01v6NGh1B7t+mqMZ9F6Iu+/QG/EIvKy4KNcuSQlSB+tvwogWHE2YsfnO31Q\n" +
            "5oEo26kGN4Vtyb8C6pBETpXomRx/0kpdeFMmfWyfjflEcHm7+CTDFaoIC2t2/sFI\n" +
            "njyZ7vmtnNL62yFm/1cRfn40vdECgYEA2UR1hQQ0GQ3FPkRFLjrKosLQVnbzAVqz\n" +
            "7DFXBkiX7EWdtZFHn+fd0FEEO35Xl++BZ1txrW1pYM+JrVJBwQDx0vd7in2mmEKh\n" +
            "j6oIdJjnJ5Zg3RoR5ziNn6o+tby84Uekn5vBVoPqsyqEnfdF/S+GhBS/1sILDXLC\n" +
            "12vrHQPJY68CgYEAi876wBEpR0IR9D+mkBafywW0m0CFKJgqdSCZXMjtVJm+dhPi\n" +
            "Wdx0rTBTV/llxAmLkCZiS2XOVHUcOabXlyDQmPJ/SpCNmaz4CjA3VqPWih3WytWI\n" +
            "O/kZCt14WvujdhuL/RuePxW2ePVONj+73dqVQVLZ2MNeJMNazpsMEmABljECgYEA\n" +
            "pgSXwEKMBLDvDTthmYe3G1O9mrRdz6UdYtf45YZHAHXZosoWbuvgJLQjhcpu6FxN\n" +
            "B0tqu9ik8yLi0xvNvssX/dq5/gMxb5tKlNqah23nA+r2rTykfYhLP7v5GynTMllq\n" +
            "Q8Piolc+2hUdLQ+r3WGZagfo5xNzGp2coLuH9q3UD2UCgYAXCeryGvcJrGhqxE+1\n" +
            "Xb75lBwkJtTWkdGfTbhJIBHGxfRj0+doLGpYgwaa0kUci5Sjd7QuNuia64Ix3+Lt\n" +
            "GwNgeQVGSW2BGFfbm4Oa7IPHioEF/B6/4eirL6cHbJIsZAdwG2fVKU++vFGfJle+\n" +
            "RbnDBAIDeeklsdHU1cY8NzN0iw==";
    /**
     * 公钥
     */
    private final static String PUBLICKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyi1NK4NnbYgL17c5U7HC\n" +
            "sF9gf741+xPyyD84SYuw2TYbpdOBNfiYRXFyUlwnHMDR+w2dQ+l94mjJHwsPl1BU\n" +
            "3Z3d/P7JoU4tSi8fjk21kXnxJHuvoQk8bi/o/tN6E0zERiA3vqOBncOhegXQjf6g\n" +
            "wTszD991BtFj4srSY95J1myyrPDH19HkNVKCJvyA0wIgrdpq/1ujbkdBbun7X3Jr\n" +
            "S5GN94Agn6wQp5HX5DBrtindyARl778zNXGVsYcsvVGJySAqPImtqKINYU+g4tNu\n" +
            "UqP2yr49qBD9nL+7i81sa++HTndxSF7Eyme4JaLD53ofuxhSGc14Ob8Dp/GWJ46U\n" +
            "3wIDAQAB";

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
        // String encrypt = RSAUtil.encryptStr(messgae);
        // System.out.println(encrypt);
        // String decrypt = RSAUtil.decryptStr(encrypt);
        // System.out.println(new String(decrypt));

    }

    @Test
    public void test03() {
        String str = "0123";
        RSA rsa = new RSA(PRIVATEKEY, PUBLICKEY);
        String s = rsa.encryptBcd(str, KeyType.PublicKey);
        String s2 = Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
        System.out.println(s);
        String s3 = new String(Base64.getDecoder().decode(s2), StandardCharsets.UTF_8);
        String s1 = rsa.decryptFromBcdToStr(s3, KeyType.PrivateKey, StandardCharsets.UTF_8);
        System.out.println(s1);
    }

    @Test
    public void test04(){
        String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIL7pbQ+5KKGYRhw7jE31hmA"
                + "f8Q60ybd+xZuRmuO5kOFBRqXGxKTQ9TfQI+aMW+0lw/kibKzaD/EKV91107xE384qOy6IcuBfaR5lv39OcoqNZ"
                + "5l+Dah5ABGnVkBP9fKOFhPgghBknTRo0/rZFGI6Q1UHXb+4atP++LNFlDymJcPAgMBAAECgYBammGb1alndta"
                + "xBmTtLLdveoBmp14p04D8mhkiC33iFKBcLUvvxGg2Vpuc+cbagyu/NZG+R/WDrlgEDUp6861M5BeFN0L9O4hz"
                + "GAEn8xyTE96f8sh4VlRmBOvVdwZqRO+ilkOM96+KL88A9RKdp8V2tna7TM6oI3LHDyf/JBoXaQJBAMcVN7fKlYP"
                + "Skzfh/yZzW2fmC0ZNg/qaW8Oa/wfDxlWjgnS0p/EKWZ8BxjR/d199L3i/KMaGdfpaWbYZLvYENqUCQQCobjsuCW"
                + "nlZhcWajjzpsSuy8/bICVEpUax1fUZ58Mq69CQXfaZemD9Ar4omzuEAAs2/uee3kt3AvCBaeq05NyjAkBme8SwB0iK"
                + "kLcaeGuJlq7CQIkjSrobIqUEf+CzVZPe+AorG+isS+Cw2w/2bHu+G0p5xSYvdH59P0+ZT0N+f9LFAkA6v3Ae56OrI"
                + "wfMhrJksfeKbIaMjNLS9b8JynIaXg9iCiyOHmgkMl5gAbPoH/ULXqSKwzBw5mJ2GW1gBlyaSfV3AkA/RJC+adIjsRGg"
                + "JOkiRjSmPpGv3FOhl9fsBPjupZBEIuoMWOC8GXK/73DHxwmfNmN7C9+sIi4RBcjEeQ5F5FHZ";

        RSA rsa = new RSA(PRIVATE_KEY, null);

        String a = "2707F9FD4288CEF302C972058712F24A5F3EC62C5A14AD2FC59DAB93503AA0FA17113A020EE4EA35EB53F"
                + "75F36564BA1DABAA20F3B90FD39315C30E68FE8A1803B36C29029B23EB612C06ACF3A34BE815074F5EB5AA3A"
                + "C0C8832EC42DA725B4E1C38EF4EA1B85904F8B10B2D62EA782B813229F9090E6F7394E42E6F44494BB8";

        byte[] aByte = HexUtil.decodeHex(a);
        byte[] decrypt = rsa.decrypt(aByte, KeyType.PrivateKey);
    }
}
