package com.tmw.javaweb;

import java.io.*;
import java.net.URL;

/**
 * @author TMW
 * @since 2020/6/12 11:20
 */
public class MyClassLoader extends ClassLoader {
    private String packageName = "com.tmw.javaweb";
    private String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> aClass = findLoadedClass(name);
        if (aClass != null) {
            return aClass;
        }
        // packageName.startsWith(name)
        if (true) {
            byte[] classData = getData(name);
            if (classData == null) {
                throw new ClassNotFoundException();
            } else {
                return defineClass(name, classData, 0, classData.length);
            }
        } else {
            return super.findClass(name);
        }
    }

    private byte[] getData(String className) {
        String path = classPath + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        try {
            // URL url = new URL(path);
            InputStream is = new FileInputStream(path);
            // InputStream is = url.openStream();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int num;
            while ((num = is.read(bytes)) != -1) {
                stream.write(bytes, 0, num);
            }
            return stream.toByteArray();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        String path = "D:/java/ideaWorkingSpace/demo/java_demo/target/classes/com/tmw/javaweb";
        MyClassLoader m1 = new MyClassLoader(path);

        Class<?> m1Class = m1.findClass("CharsetDemo");
        System.out.println(m1Class.newInstance());
        MyClassLoader m2 = new MyClassLoader(path);

        Class<?> m2Class = m2.findClass("CharsetDemo");
        System.out.println(m2Class.newInstance());

    }

}
