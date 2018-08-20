package com.javase.classloader;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 自定义加载器
 */
public class CustomClassLoader_20180704 extends ClassLoader
{
    public static final String drive = "F:/tuofu2017/learn/技术点/classloader/";
    public static final String fileType = ".class";

    /**
     * 自定义加载器并读取加在类的信息
     */
    @Test
    public void test1() throws Exception
    {
        CustomClassLoader_20180704 loader = new CustomClassLoader_20180704();
        //该类需要无包路径
        Class<?> objClass = loader.loadClass("ClassloaderTest", true);

        Method[] methods=objClass.getDeclaredMethods();
        for (Method method:methods){
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println(parameter.getType() + ";" + parameter.getName());
            }
            method.invoke(null,"李四",100);
        }

        Object obj = objClass.newInstance();
        System.out.println("类的名称:"+objClass.getName());
        System.out.println("自定义加载器"+objClass.getClassLoader());
        System.out.println("自定义加载器-->父加载器:"+objClass.getClassLoader().getParent());
        System.out.println("自定义加载器-->父加载器2:"+objClass.getClassLoader().getParent().getParent());
        System.out.println("实例类的名称:"+obj.getClass().toString());

    }

    /**
     * 默认加载器并读取加在类的信息
     */
    @Test
    public  void test2(){
        ClassLoader nativeClassLoader=CustomClassLoader_20180704.class.getClassLoader();
        try {
            Class<?> objClass =nativeClassLoader.loadClass("ClassloaderTest");
            Method[] methods=objClass.getDeclaredMethods();
            for (Method method:methods) {
                Parameter[] parameters = method.getParameters();
                for (Parameter parameter : parameters) {
                    System.out.println(parameter.getType() + ";" + parameter.getName());
                }
                method.invoke(null,"张三",100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("默认的加载器,系统自带的:"+nativeClassLoader);
        System.out.println("默认的加载器,系统自带的-->父加载器:"+nativeClassLoader.getParent());
        System.out.println("自定义加载器-->父加载器2:"+nativeClassLoader.getParent().getParent());
    }

    public Class<?> findClass(String name)
    {
        byte[] data = loadClassData(name);
        return defineClass(name, data, 0, data.length);// 将一个 byte 数组转换为 Class// 类的实例
    }
    public byte[] loadClassData(String name)
    {
        FileInputStream fis = null;
        byte[] data = null;
        try
        {
            fis = new FileInputStream(new File(drive + name + fileType));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int ch = 0;
            while ((ch = fis.read()) != -1)
            {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return data;
    }
}