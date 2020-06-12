package com.tmw.javaweb;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author TMW
 * @date 2020/6/11 16:39
 */
public class SerializableTest implements Serializable {

    private static final long serialVersionUID = -434444306773550883L;

    public int num = 1390;

    public static void main(String[] args) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\java\\ideaWorkingSpace\\tmw\\learning_demo\\java_demo\\src\\main\\resources\\a.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            SerializableTest serializable = new SerializableTest();
            objectOutputStream.writeObject(serializable);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
