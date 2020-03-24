package com.tmw.thread.block;

import com.tmw.util.ThreadPoolUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试生产消费阻塞
 *
 * @author TMW
 * @since 2020/3/22 9:46
 */
public class TestPC {

    public static void main(String[] args) {
        Container container = new Container();
        Runnable producerRunnable = () -> {
            for (int i = 0; i < 100; i++) {
                try {
                    container.producer(new Chicken(i + 1));
                    System.out.println("生产鸡的序号：" + (i + 1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        Runnable consumerRunnable = () -> {
            for (int i = 0; i < 100; i++) {
                try {
                    container.producer(new Chicken(i + 1));
                    System.out.println("消费鸡的序号：" + (i + 1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        ThreadPoolUtil.getThreadPool().execute(producerRunnable);
        ThreadPoolUtil.getThreadPool().execute(consumerRunnable);
        ThreadPoolUtil.sleep(5);
        ThreadPoolUtil.shutdown();
    }

    static class Chicken {
        private int index;

        public Chicken(int index) {
            this.index = index;
        }
    }

    static class Container {
        private Chicken[] chickens = new Chicken[10];
        private AtomicInteger count = new AtomicInteger(0);

        public synchronized void producer(Chicken chicken) throws InterruptedException {
            // 容器满了，生产者等待
            if (count.get() == 10) {
                this.wait();
            }

            // 通知消费者进行消费
            chickens[count.getAndIncrement()] = chicken;
            this.notifyAll();
        }

        public synchronized void consumer() throws InterruptedException {
            // 通知生产者进行消费，消费者暂停
            if (count.get()  == 0) {
                this.wait();
            }

            Chicken chicken = chickens[count.decrementAndGet()];
            this.notifyAll();
        }

    }

}
