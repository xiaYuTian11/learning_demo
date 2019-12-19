package com.demo.concurrent;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author TMW
 * @date 2019/12/18 16:18
 */
public class FileCrawler implements Runnable {
    private final BlockingQueue<File> fileQueue = null;
    private final FileFilter fileFilter = null;
    private final File root = null;

    @Override
    public void run() {

    }

    public void crawl(File root) {
        File[] entries = root.listFiles(fileFilter);
        if (entries != null) {
            for (File entry : entries) {
                if (entry.isDirectory()) {
                    crawl(entry);
                } else if (false) {
                    ExecutorService executorService = Executors.newFixedThreadPool(100);
                    executorService.execute(() -> {
                        System.out.println("123");
                    });
                    executorService.shutdown();
                    ExecutorService executorService1 = Executors.newCachedThreadPool();
                    // Executor
                }
            }
        }

    }

}
