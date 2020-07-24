package com.tmw;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author TMW
 * @date 2020/7/24 16:44
 */
public class BlogPageProcessor implements PageProcessor {
    private final Site site = Site.me();
    @Override
    public void process(Page page) {
        System.out.println(page);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new BlogPageProcessor()).addUrl("https://juejin.im/post/5e774dace51d4526f16e6f66").run();
    }
}
