package com.task.JavaJvmTask.task;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author : dabing
 * @date : 2020/10/20
 */
public class TaskClassLoader extends ClassLoader {


    public static void main(String[] args) {
        try {
            Class helloClass = new TaskClassLoader().findClass("Hello");
            Object obj = helloClass.newInstance();
            Method method = helloClass.getMethod("hello");
            method.invoke(obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = this.getClass().getResource("/").getPath() + name + ".xlass";//获取文件路径
        File file = new File(fileName);

        int size = Long.valueOf(file.length()).intValue();
        byte[] buf = new byte[size];
        if (file.isFile()) {
            // 以字节流方法读取文件
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                fis.read(buf);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("文件不存在！");
        }

        for(int j=0;j<buf.length;j++){
            buf[j] = intToByte(255 - byteToInt(buf[j]));
        }

        return defineClass(name, buf, 0, buf.length);
    }

    //byte 与 int 的相互转换
    public static byte intToByte(int x) {
        return (byte) x;
    }

    public static int byteToInt(byte b) {
        //Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
        return b & 0xFF;
    }

}
