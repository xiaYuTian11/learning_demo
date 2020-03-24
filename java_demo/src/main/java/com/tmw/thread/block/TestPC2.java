package com.tmw.thread.block;

import com.tmw.util.ThreadPoolUtil;

/**
 *
 *
 * @author TMW
 * @since 2020/3/22 10:36
 */
public class TestPC2 {
    public static void main(String[] args) {
        TV tv = new TV();
        // new Thread(new Player(tv)).start();
        // new Thread(new Watcher(tv)).start();
        ThreadPoolUtil.getThreadPool().execute(new Player(tv));
        ThreadPoolUtil.getThreadPool().execute(new Watcher(tv));
        ThreadPoolUtil.shutdown();
    }
}

class Player implements Runnable {
    TV tv;

    public Player(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                tv.play("欢乐喜剧人");
                // System.out.println("欢乐喜剧人");
            } else {
                tv.play("荒野求生");
                // System.out.println("荒野求生");
            }
        }
    }
}

class Watcher implements Runnable {
    TV tv;

    public Watcher(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            tv.watch();
        }
    }
}

class TV {
    String voice;
    boolean flag = true;

    public synchronized void play(String voice) {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("演员表演了：" + voice);
        this.voice = voice;
        this.notifyAll();
        flag = !flag;
    }

    public synchronized void watch() {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观众观看了：" + voice);
        this.notifyAll();
        flag = !flag;
    }
}
