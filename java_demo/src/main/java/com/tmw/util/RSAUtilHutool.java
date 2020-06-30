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
public class RSAUtilHutool {

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
    // private final static String PRIVATEKEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQClubxrHxMkiUAVI6ydLk89VsvFu71AnzdJ986ZLJRQLmhcfIuMeHaHlFhSLGiLGcft0NAauRocoGndGZrkko8lVFO1cuOrpC9WytuEK0/wjDE43t6wYcQyavNKExBVTHY3CGf9NC8UoSVzim99aYLO5LmWaCbIWKfJ3iIxC6d8U+0N9DPQ+NPMlimk+TBzwBogI1cVIHK0hvvnFTUFEhFCLKUA56uR8tP+Mq0tXW2xFlRrK262qYWbuEGjIoZFgeCVwAPWim40xYIxqj9qAKy74SaTBqHv+Dj09hgY+3hP4GmqkNR4p5GfYM02cmtTj1D1kHRri4Emu0ovzor2t9kfAgMBAAECggEBAJzQdI60V8vBp4aZPBN7J3W2NJlE/V4xigKO3son2atJTmz9WJqxvg6qeyw+YyoEWh8Y8GHo9uFRlPqm6N8SIytCcPcH4JCgij7JMxAMX9cRAN58XSFvUVQXyWA1S8Y61L1cUfHQuCAAH80FmFuGREV7PnUo1lHLOfGVJKteCLxls+EAPplOaTysf+m3N4JVL3faLYFrDIY3NWvzPUqi4VWNzrTsU9vNsZ+7YqJlsZLCDyoVsLWrdGUf+uuBzmaQKNYQwLLPEVkhdVnxEnrIn2lDXoGpqIzYgn3wNa9xigS8L3bS52z0cDeUjq/k0DJ3dDLiobZyUyDwdG43hK1Wp7kCgYEAzz2ILkYk4H9OlVR+hgbfPGlacNE8eBXoTLTeJMmcIcCid8auvfRR7yqYE0DcZtV3qaVUOXHtIfYA5Mwxjvlmg17eL6ncEHdHpeZ3Fd4l3Fb4oV6wqHvjwJH7NnglFF9o+7xrKvdU7qrBUCCJPk/zHH/SwTnHpv6sNngZD4K78d0CgYEAzLewidoVpd6Xl9r+wPVtULypHYBxv6wkBYHYPhezqPhp8m5aTAwIths6QbB2bP/PDsoY7vftMkBdNRqfqpDG0U9Usi5O/yJaEC3UkEjaqzMqLxQPp4P4EjqrcMLxhd4ahSOG5E2gVx79xlvSyuP7SlVsG8Gjxy+8INR8kEJ2DSsCgYEAv9GAXvRl5bVwEyAmDAAIWG5bW9kPU0dTwMwYYYyKi2M1/g9UZlXiELEoGYoLdtNW8xsoHhOpHaoJobyuklud2zLQDOX33vXIx3HN/9uix5NohlSSxyAE6pU0JHKIP5cgIQGBYD+VyWMbkwLKhXaS22oktNy6Q4Ot+qym5YhoVbECgYB3Szt2uT0QMUwJU7ZMXzhaafXqmSSv2vIKu4V1eX5aIpgqi5/W3NReujMSgHAiSFcyeRZaeuMf+13VSjRswnz5D899HXsvNadh8jMB+VJjnSch1ADjTwXKKaIpzY/MwIaXuwIqHAopeofLhUqqrHuM74ZWDlsQAYMRZoFH11LPjQKBgCeclVNeH82jOYR6A9AJjp85gclInO1Y4LBwzw4b80BYr1MS1CRPOdLwe1biPzOsn7PfNa1vHvcMfVu51GH+KY2SwpxPL74/ntBThiqpBFte9Ye7TtSDUEjHRlyxOBWXZSAmpFr0FUjhKAqGMmxwl9KIDdjgr2RndDHxA0RESx82";
    /**
     * 公钥
     */
    // private final static String PUBLICKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwSuf1LuxJIfgYLaJehMj\n" +
    //         "Z03eD5X4fqVxTWxTD4b6LKA09/VkIL/UH05wpVCynYUhGVliW4ivSipA/UJ+XJVv\n" +
    //         "5SPZYx6YApwH7f9t5H11VnWmJm4CQloTRXpJ3afzYYEdZ5yMpuRspuRPy8q223bc\n" +
    //         "nZuvkXiZKoX3uJ2pG57GDMHRJCGwEfL0Qe/MktNPBAqdKeNC8smK19sGI2IuwnKD\n" +
    //         "32r5aEPs15JHuj7R0fgKL5rz5LdkDFUdfXOwCWRL5Jy5BpYiiPalKPgs/Vl5o302\n" +
    //         "3P81mRsM3eeuI88nMksAbqSJUYxBhSK4jMEtZ2hVYo54/KC/vhdyRdQnm8uEr2L2\n" +
    //         "pQIDAQAB";
    /**
     * 工行公钥
     */
    private final static String PUBLICKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAihTj8ksHMQmyrwUVTR213j3iyoL3HJ1X48laOKZNSThyC+6crGupyNXeuz1RuNCZZ1NWiWXOLZKKPFnzyx1swgUQho5wkrA5QqN0fT0GBt35L59OZXHbfdY7coDJCce1rhhZzEML1KDIF7MxO+yA0FiOLj93iRrXj37jICLLLNS1p0XPTF2FJKHrEgABFmyBpbPODT109Tx5U5eGrwUk/1I+WKT6w2VTpMkXEm0794OgbE9j/SqTqWvxKjc19rh949qOxApPPmscmFM1uIdvB1K5fOAsE5hR9Noqwmobq6lJWK4tT2O1NA8J/T74htBspRat1dp5XlRb5IMKejVHDwIDAQAB";

    /**
     * 加解密对象
     */
    private final static RSA RSA = new RSA(PRIVATEKEY, PUBLICKEY);

    /**
     * 加密 返回字节,指定编码
     *
     * @return
     */
    public static String encrypt(String message) {
        String encryptBcd = RSA.encryptBcd(message, KeyType.PublicKey, StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(encryptBcd.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 解密 返回字节
     *
     * @return
     */
    public static String decrypt(String data) {
        return new String(RSA.decrypt(Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8)), KeyType.PrivateKey));
    }

}
