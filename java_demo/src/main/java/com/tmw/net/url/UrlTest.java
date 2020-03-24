package com.tmw.net.url;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author TMW
 * @since 2020/3/20 16:28
 */
public class UrlTest {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://www.bilibili.com/");
        // URL url = new URL("https://www.baidu.com/");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File("hello_url")));
        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }
        outputStream.close();
        inputStream.close();
        httpURLConnection.disconnect();
    }
}
