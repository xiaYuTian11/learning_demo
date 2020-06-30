package com.tmw.url;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author TMW
 * @since 2020/6/28 20:05
 */
public class UrlEncodeTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("EOcABOVURKEGKbZm4Z3erT1KU0PCffVtH9mrSG6YQK5u9K7zpUyg+E6uRDW6pQ2pGBX7zmvUCDTTuioelX0vepAM3peuuJL2BTCdTOIiN/5wM7XLaa4ouUH5oCQ3DhYs/EXswOtvYnavD3WeL9xwz89XniYHPQO8QCqU+jjxwNa3RiIv9tMKQRwuoK9ys7o04TulvGtQ0PJhodhzPCEFS4RelxD3jdlXObUfYokAU1g2mfwX1XoE6umHbFAdCWoU+W5Klj4kX2Mn3b0wccrD0knGiSk5X2qq63ebdoppxC4+uf8Xpl3Iqw0MN6QSkilzW7eV9a+FO/jUiDzx2qA1jQ==", StandardCharsets.UTF_8.toString());
        System.out.println(encode);
        System.out.println(URLDecoder.decode(encode,StandardCharsets.UTF_8.toString()));
    }
}
