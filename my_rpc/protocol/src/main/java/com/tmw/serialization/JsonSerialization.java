package com.tmw.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmw.api.Serialization;

import java.io.IOException;

/**
 * 序列化 和反序列化
 *
 * @author TMW
 * @date 2020/7/23 15:48
 */
public class JsonSerialization implements Serialization {
    private final ObjectMapper objectMapper;

    public JsonSerialization() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public <T> byte[] serialize(T obj) {
        try {
            return objectMapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T deSerialize(byte[] data, Class<T> clz) {
        try {
            return objectMapper.readValue(data, clz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
