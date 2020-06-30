package com.tmw.enumTest;

/**
 * 因为枚举只会在类加载时装载一次，所以它是线程安全的，这也是《Effective Java》作者极力推荐使用枚举来实现单例的主要原因。
 *
 * @author TMW
 * @date 2020/6/28 10:14
 */
public class Singleton {

    public enum SingletonEnum {
        INSTANCE;
        private final Singleton instance;

        SingletonEnum() {
            instance = new Singleton();
        }

        private Singleton getInstance() {
            return instance;
        }
    }

    public static void main(String[] args) {
        Singleton instance = SingletonEnum.INSTANCE.getInstance();
        System.out.println(instance.equals(SingletonEnum.INSTANCE.getInstance()));
        System.out.println(SingletonEnum.INSTANCE.getInstance());
        System.out.println(SingletonEnum.INSTANCE.getInstance());
        System.out.println(SingletonEnum.INSTANCE.getInstance());
        System.out.println(SingletonEnum.INSTANCE.getInstance());
    }
}
