package com.tmw.api;

/**
 * @author TMW
 * @date 2020/7/23 15:50
 */
public interface Serialization {
    /**
     * 序列化
     *
     * @param obj
     * @param <T>
     * @return
     */
    <T> byte[] serialize(T obj);

    /**
     * 反序列化
     *
     * @param data
     * @param clz
     * @param <T>
     * @return
     */
    <T> T deSerialize(byte[] data, Class<T> clz);
}
