package com.demo.face;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.baidu.aip.face.AipFace;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.maxicode.MaxiCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.QRCode;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 百度云人脸技术
 *
 * @author TMW
 * @since 2020/2/13 12:51
 */
public class Demo01 {
    private static AipFace aipFace;

    @BeforeAll
    public static void test() {
        aipFace = new AipFace("18459233", "BEvGcn8rPvXPa7pNNK3F6hS9", "GsqV2UH8RbSF2ar52Kfeb39bUsdDIi9P");
    }

    /**
     * 人脸注册
     */
    @Test
    public void testFaceRegister() throws IOException {
        // AipFace aipFace = new AipFace("18459233", "BEvGcn8rPvXPa7pNNK3F6hS9", "GsqV2UH8RbSF2ar52Kfeb39bUsdDIi9P");

        HashMap<String, String> options = new HashMap<>();
        options.put("quality_control", "LOW");
        options.put("liveness_control", "NONE");
        options.put("image_type", "BASE64");
        options.put("group_id", "demo01");
        options.put("user_id", "00001");
        options.put("user_info", "第一个测试");
        String jsonStr = JSONUtil.toJsonStr(options);
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
        String accessToken = "[调用鉴权接口获取的token]";
        String result = HttpUtil.createPost(url + "?access_token=" + accessToken).header("Content-Type", "application/json").body(jsonStr).execute().body();
        // String result = HttpUtil.post(url, accessToken, "application/json", jsonStr);
        System.out.println(result);
        String filePath = "E:\\图片\\5e97e105667e9ff8f422c916e0d9ee3.jpg";
        byte[] readAllBytes = Files.readAllBytes(Paths.get(filePath));
        String encode = Base64Encoder.encode(readAllBytes);
        JSONObject jsonObject = aipFace.addUser(encode, options.get("image_type"), options.get("group_id"), options.get("user_id"), options);
        System.out.println(jsonObject);
    }

    /**
     * 人脸更新
     */
    @Test
    public void testFaceUpdate() throws IOException {
        HashMap<String, String> options = new HashMap<>();
        options.put("quality_control", "LOW");
        options.put("liveness_control", "NONE");
        options.put("image_type", "BASE64");
        options.put("group_id", "demo01");
        options.put("user_id", "00001");
        options.put("user_info", "第一个测试");
        // String filePath = "E:\\图片\\5e97e105667e9ff8f422c916e0d9ee3.jpg";
        String filePath = "E:\\图片\\测试图片.jpg";
        byte[] readAllBytes = Files.readAllBytes(Paths.get(filePath));
        String encode = Base64Encoder.encode(readAllBytes);
        JSONObject jsonObject = aipFace.updateUser(encode, options.get("image_type"), options.get("group_id"), options.get("user_id"), options);
        System.out.println(jsonObject);
    }

    @Test
    public void testFaceDetect() throws IOException {
        String filePath = "E:\\图片\\5e97e105667e9ff8f422c916e0d9ee3.jpg";
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        String encode = Base64Encoder.encode(bytes);
        HashMap<String, String> options = new HashMap<>();
        options.put("max_face_num", "10");
        JSONObject jsonObject = aipFace.detect(encode, "BASE64", options);
        System.out.println(jsonObject);
    }

    @Test
    public void testCode() throws WriterException, IOException {
        String url = "何明珠，我喜欢你";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix encode = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 200, 200, hints);
        MatrixToImageWriter.toBufferedImage(encode);
        MatrixToImageWriter.writeToPath(encode, "jpg", new File("E:\\图片\\ceshiQR.jpg").toPath());
    }

}
