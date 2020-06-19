package com.tmw.pad;

import cn.hutool.core.net.URLEncoder;
import com.google.common.base.Charsets;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author TMW
 * @date 2020/6/17 11:18
 */
public class PadTest {

    public static void main(String[] args) {
        String url = "\\upload\\impFile\\Photos\\af6aecce07b64600a4e688ddb643f56f.jpg";
        if (Objects.nonNull(url) && url.contains("\\")) {
            url = url.replace("\\", "/");
        }
        final int lastIndexOf = url.lastIndexOf("/");
        final String dir = url.substring(1, lastIndexOf);
        System.out.println(dir);
    }

    @Test
    public void test01() {
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<String>() {{
            add("1");
            add("2");
        }};
        List<String> list2 = new ArrayList<String>() {{
            add("3");
            add("4");
        }};
        list.addAll(list1);
        System.out.println(list);
        list.addAll(list.size(), list2);
        System.out.println(list);
    }

    @Test
    public void test02() throws Exception {
        String url = "http://139.9.233.106:9098/api";
        String fileName = "/upload/impFile/Photos/2c9f7209db054339b961e8695349247520200618110832869.jpg";
        String encode = url + URLEncoder.DEFAULT.encode(fileName, Charsets.UTF_8);
        System.out.println(encode);

        FileUtils.copyURLToFile(new URL(encode), new File("C:\\Users\\Administrator\\Desktop\\测试\\upload/impFile/Photos/2c9f7209db054339b961e8695349247520200618110832869.jpg"));

    }

}
