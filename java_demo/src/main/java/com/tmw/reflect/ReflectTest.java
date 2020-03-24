package com.tmw.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author TMW
 * @since 2020/3/22 11:11
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception {
        Method test01 = ReflectTest.class.getMethod("test01", List.class, Map.class);
        Type[] genericParameterTypes = test01.getGenericParameterTypes();
        for (Type genericParameterType : genericParameterTypes) {
            System.out.println("genericParameterType" + genericParameterType);
            if (genericParameterType instanceof ParameterizedType) {
                ParameterizedType parameterType = (ParameterizedType) genericParameterType;
                Type[] actualTypeArguments = parameterType.getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println("actualTypeArguments" + actualTypeArgument);
                }
            }
        }
    }

    public void test01(List<Object> objectList, Map<String, Integer> map) {
        System.out.println("test01");
    }

    public Map<String, Integer> test02(List<Object> objectList, Map<String, Integer> map) {
        System.out.println("test02");
        return null;
    }
}
